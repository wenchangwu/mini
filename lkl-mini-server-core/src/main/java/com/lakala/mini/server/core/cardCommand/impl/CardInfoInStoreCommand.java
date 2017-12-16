package com.lakala.mini.server.core.cardCommand.impl;

import com.lakala.mini.common.CardState;
import com.lakala.mini.common.CardType;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.common.OperateType;
import com.lakala.mini.exception.CardNumNonComplianceException;
import com.lakala.mini.exception.UserCardHasExistException;
import com.lakala.mini.server.core.cardCommand.AbstractCardCommand;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.CardResource;
import com.lakala.mini.server.core.domain.CardUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author 刘文成
 * @version 创建时间：2010-12-21 上午10:06:45 类说明，用户卡入库
 */
@Component(value = "cardInfoInStoreCommand")
public class CardInfoInStoreCommand extends AbstractCardCommand {

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly=false)
	public String execute(Object obj) throws Exception {
		logger.debug("系统接受到的参数对象为：{}",obj);
		
		if(obj instanceof BufferedReader){
			BufferedReader br = (BufferedReader) obj;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String topLine = br.readLine();// 开卡日期：YYYYMMDD\t批次号：DDDDDD\t制卡数：DDDD
			String top[] = topLine.split("\t");
			String openDate_str = StringUtils.split(top[0], ":")[1];// 开户日期
			String batchNo = StringUtils.split(top[1], ":")[1];// 批次号
			String cardNum = StringUtils.split(top[2], ":")[1];// 制卡数

			Timestamp enableDate = new Timestamp(sdf.parse(openDate_str).getTime());
			int cardNum_int = Integer.parseInt(cardNum);

			String line = null;
			int count = 0;

			while (br.ready()) {
				line = br.readLine();
				// 空行为数据结束标记
				if (line.trim().equals("")) {
					// logger.info("读取用户卡文件遇到空行");
					break;
				}
				String cardLine[] = StringUtils.split(line, "\t");
				if (cardResourceDAO.get(cardLine[0]) != null) {
					logger.info("用户卡入库，卡号为：" + cardLine[0]
							+ "的用户卡已存在系统中，所有数据回滚，该批次入库失败");
					throw new UserCardHasExistException();
				}

				CardResource cardResource = new CardResource();
				cardResource.setCardNo(cardLine[0]);
				cardResource.setTrack2(cardLine[1]);
				cardResource.setPasswd(cardLine[2]);
				cardResource.setStatus(CardState.INITIALIZATION);
				cardResource.setStance(Integer.parseInt(batchNo));
				cardResource.setEnableDate(enableDate);
				cardResource.setReqDate(new Timestamp(new Date().getTime()));
				cardResource.setCardBin(cardLine[0].substring(0, 7));

				cardResourceDAO.save(cardResource);
				count++;
			}
			// 如果入库数据与制卡数不一致，数据库回滚
			if (count != cardNum_int) {
				logger.info("用户卡入库，入库:" + count + "张用户卡，与制卡数：" + cardNum + "不一致");
				throw new CardNumNonComplianceException();

			}

			return CodeDefine.SUCCESS;
		}
		/**
		 * Add by QW
		 * 从页面填入信息，直接入库
		 */
		if( obj instanceof List){
			Iterator cardResources = ((List)obj).iterator();
			int count = 0;
			while(cardResources.hasNext()){
				logger.debug("需入库的卡原始信息为：{}",cardResources.toString());
				CardResource cardResource = (CardResource) cardResources.next();
				if(cardResourceDAO.getBy("cardNo", cardResource.getCardNo()) != null){
					logger.info("用户卡入库，卡号为：" + cardResource.getCardNo()
					        							+ "的用户卡已存在系统中，所有数据回滚，该批次入库失败");
					throw new UserCardHasExistException();
				}
				cardResource.setStatus(CardState.INITIALIZATION);
//				cardResource.setStance(Integer.parseInt(batchNo));
//				cardResource.setEnableDate(enableDate);
				cardResource.setReqDate(new Timestamp(new Date().getTime()));
				cardResource.setCardBin(cardResource.getCardNo().substring(0, 7));

				cardResourceDAO.save(cardResource);
				CardInfo cardInfo = new CardInfo();
				cardInfo.setCardNo(cardResource.getCardNo());
				cardInfo.setPasswd(cardResource.getPasswd());
				cardInfo.setPvn(cardResource.getPvn());
				cardInfo.setStatus(CardState.UNINITIALIZATION);
				String track2 = cardResource.getTrack2();
//				if(track2 !=null && !"".equals(track2) && track2.indexOf(";") >= 0 && track2.indexOf("?") >= 0)
//					track2 = cardResource.getTrack2().substring(cardResource.getTrack2().indexOf(";")+1, cardResource.getTrack2().indexOf("?"));
				cardInfo.setTrack2(track2);
				cardInfo.setType(CardType.USER_CARD);
				cardInfo = cardInfoDAO.save(cardInfo);
				CardUser cardUser = new CardUser();
				cardUser.setCardInfoId(cardInfo.getId());
				cardUser.setTelMovingCount(0);
				cardUser.setOperatType(OperateType.IN_STORAGE);
				cardUser.setChangeDate(new Date());
				cardUserDAO.save(cardUser);
				count++;
			}
			if (count >= 0) {
				logger.info("用户卡入库，入库:" + count + "张用户卡,时间：" + (new Timestamp(System.currentTimeMillis())));
			}	
			return CodeDefine.SUCCESS;
		}

		return CodeDefine.OTHER_ERR;
	}


}
