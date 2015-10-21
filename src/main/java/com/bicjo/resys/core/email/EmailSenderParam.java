package com.bicjo.resys.core.email;

public class EmailSenderParam {
	private String email;
	private EmailNotificationType type;

	public String getEmail() {
		return email;
	}

	public EmailSenderParam setEmail(String email) {
		this.email = email;
		return this;
	}

	public EmailNotificationType getType() {
		return type;
	}

	public EmailSenderParam setType(EmailNotificationType type) {
		this.type = type;
		return this;
	}

}
