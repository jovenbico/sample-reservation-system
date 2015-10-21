package com.bicjo.resys.core.email.impl;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;

import com.bicjo.resys.core.email.EmailSender;
import com.bicjo.resys.core.email.EmailSenderParam;

public class EmailSenderImpl implements EmailSender {

	private final Logger LOG = Logger.getLogger(getClass());

	@Override
	@Async
	public void notify(EmailSenderParam param) {
		LOG.debug("Email send (" + param.getType() + ") to " + param.getEmail());
	}

}
