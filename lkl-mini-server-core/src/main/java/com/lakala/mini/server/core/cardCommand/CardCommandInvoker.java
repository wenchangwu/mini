package com.lakala.mini.server.core.cardCommand;

import com.lakala.mini.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午02:39:50
 * 类说明,执行卡操作的命令接口包装
 */

@Component(value="cardCommandInvoker")
public class CardCommandInvoker implements ICardCommandInvoker {
	@Autowired
	private ICommand cardInfoInStoreCommand;
	@Autowired
    private ICommand psamInStoreCommand;
	@Autowired
    private ICommand manuOpenCommand;
	@Autowired
    private ICommand manuMoveCommand;
	@Autowired
	private ICommand udPsamInStoreCommand;
	@Autowired
	private ICommand miniUseCardPsamInStoreCommand;
	@Autowired
	private ICommand sjPsamInStoreCommand;
	@Autowired
	private ICommand manualPsamInStoreCommand;
	
	@Autowired
	private ICommand posPlusPsamInStoreCommand;
    
    
	@Override
	public String execute(Object obj,int command) throws Exception {
		
		if(command == CARDINFOINSTORECOMMAND)
			return cardInfoInStoreCommand.execute(obj);
		else if(command == PSAMINFOINSTORECOMMAND)
			return psamInStoreCommand.execute(obj);
		else if(command == MANUOPENCOMMAND)
			return manuOpenCommand.execute(obj);
		else if(command == MANUMOVECOMMAND)
			return manuMoveCommand.execute(obj);
		else if(command == UD_PSAMINFOINSTORECOMMAND)
			return udPsamInStoreCommand.execute(obj);
		else if(command == SJ_PSAMINFOINSTORECOMMAND)
			return sjPsamInStoreCommand.execute(obj);
		else if(command == MANUAL_PSAMINFOINSTORECOMMAND)
			return manualPsamInStoreCommand.execute(obj);
		else if(command == MINI_USECARD_PSAMINFOINSTORECOMMAND)
			return miniUseCardPsamInStoreCommand.execute(obj);
		else if(command == POS_PLUS_PSAMINFOINSTORECOMMAND)
			return posPlusPsamInStoreCommand.execute(obj);
		else
			throw new NotExistException(command+"命令不存在");
	}

	public void setCardInfoInStoreCommand(ICommand cardInfoInStoreCommand) {
		this.cardInfoInStoreCommand = cardInfoInStoreCommand;
	}

	public void setPsamInStoreCommand(ICommand psamInStoreCommand) {
		this.psamInStoreCommand = psamInStoreCommand;
	}

	public void setManuOpenCommand(ICommand manuOpenCommand) {
		this.manuOpenCommand = manuOpenCommand;
	}

	public void setManuMoveCommand(ICommand manuMoveCommand) {
		this.manuMoveCommand = manuMoveCommand;
	}
    
}
