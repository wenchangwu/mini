package com.lakala.mini.server.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;

@Component
public class Config implements Serializable, InitializingBean {

	private static final long serialVersionUID = 1473485304943066L;
	private Logger logger = LoggerFactory.getLogger(Config.class);

	@Value(value = "${mini.msg.changeCardUserAuditingFailureMsgTemplate}")
	private String changeCardUserAuditingFailureMsgTemplate;

	@Value(value = "${mini.msg.passwordRestMsgTemplate}")
	private String passwordRestMsgTemplate;

	public String getChangeCardUserAuditingFailureMsgTemplate() {
		return changeCardUserAuditingFailureMsgTemplate;
	}

	public String getPasswordRestMsgTemplate() {
		return passwordRestMsgTemplate;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.hasText(this.getChangeCardUserAuditingFailureMsgTemplate(),
				"Properties: mini.msg.changeCardUserAuditingFailureMsgTemplate must has text!");
		logger.info("ChangeCardUserAuditingFailureMsgTemplate is:{}",
				this.getChangeCardUserAuditingFailureMsgTemplate());
		Assert.hasText(this.getPasswordRestMsgTemplate(),
				"Properties: mini.msg.passwordRestMsgTemplate must has text!");
		logger.info("PasswordRestMsgTemplate is:{}",
				this.getPasswordRestMsgTemplate());
	}

}
