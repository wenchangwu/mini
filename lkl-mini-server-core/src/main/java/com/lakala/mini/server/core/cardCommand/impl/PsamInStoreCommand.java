package com.lakala.mini.server.core.cardCommand.impl;

import com.lakala.common.util.Tools;
import com.lakala.mini.common.CardState;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.exception.PsamCardBindException;
import com.lakala.mini.exception.UserCardBindException;
import com.lakala.mini.exception.UserCardNotFoundException;
import com.lakala.mini.server.core.cardCommand.AbstractCardCommand;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.CardOrg;
import com.lakala.mini.server.core.domain.CardResource;
import com.lakala.mini.server.core.domain.PsamInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 上午09:40:36
 * 类说明，PSAM卡入库
 */


@Component(value="psamInStoreCommand")
public class PsamInStoreCommand extends AbstractCardCommand {
	
	@Override	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String execute(Object obj) throws Exception {
		BufferedReader br = (BufferedReader)obj;
		String line = null;
		String batchNo = Tools.getPlatSeq();//		生成批次号		
		int count = 0;

		while(br.ready()){
			line = br.readLine();//终端PSAM号,商户标识,用户卡号
			//空行为数据结束标记
			if(line.trim().equals("")){
//				logger.info("读取用户卡文件遇到空行");
				break;
			}
			String cardLine[] = line.split(",");
			
/*************************保存PSAM卡数据*****************************/
			PsamInfo psamInfo = psamInfoDAO.getBy("pasmNo",cardLine[0]);
			if(psamInfo != null){				
				if(psamInfo.getPasmState() != CardState.BINGDING){
//				未绑定，记录PSAM卡历史
					psamInfoHisDAO.save(psamInfo);					
					psamInfoDAO.inStore(cardLine[0],batchNo,psamInfo);
				}else{
					logger.info("卡号为："+cardLine[0]+"的PSAM卡已绑定，所有数据回滚，该批次入库失败");
					throw new PsamCardBindException();
				}				
			}else{				
				psamInfo = psamInfoDAO.inStore(cardLine[0],batchNo,null);
			}
/*************************保存用户卡信息********************************/
			CardInfo cardInfo = null;
			if(!cardLine[2].trim().equals("")){
				cardInfo = cardInfoDAO.getBy("cardNo", cardLine[2]);
//				检查用户卡状态
				CardResource cardResource = cardResourceDAO.get(cardLine[2].trim());
				if(cardResource == null){
					logger.info("卡号为："+cardLine[0]+"的PSAM卡对应的用户卡"+cardLine[2]+"不存在，所有数据回滚，该批次入库失败");
					throw new UserCardNotFoundException();
					
				}
				if(cardResource.getStatus() == CardState.BINGDING){
					logger.info("卡号为："+cardLine[0]+"的PSAM卡对应的用户卡"+cardLine[2]+"已绑定，所有数据回滚，该批次入库失败");
					throw new UserCardBindException();
				}
				if(cardResource.getStatus() == CardState.BAD){
					logger.info("卡号为："+cardLine[0]+"的PSAM卡对应的用户卡"+cardLine[2]+"被标记为废卡，所有数据回滚，该批次入库失败");
					throw new UserCardNotFoundException();
				}

//				根据商户标识查询商户
				CardOrg cardOrgInfo = null;
				if(!cardLine[1].trim().equals(""))
					cardOrgInfo = cardOrganizationManager.getByCode(cardLine[1],null);
				
				if(cardInfo != null){					
					cardInfoHisDAO.save(cardInfo);					
					cardInfoDAO.update(cardInfo,cardResource,cardOrgInfo);
				}else{					
					cardInfo = cardInfoDAO.save(cardInfo,cardResource,cardOrgInfo);
				}
				
			}else{
				cardInfo = new CardInfo();
				cardInfo = cardInfoDAO.save(cardInfo);				
			}
/*************************保存卡用户表*************************************/
			cardUserDAO.init(psamInfo,cardInfo);			
			count++;
		}
		logger.info("共入库PSAM卡："+count+"张，入库批次号："+batchNo);

		return CodeDefine.SUCCESS;
	}
}
