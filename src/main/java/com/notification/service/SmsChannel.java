package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.notification.model.ChannelType;
import com.notification.util.SmsValidator;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.creator.api.v2010.account.MessageCreator;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.type.PhoneNumber;

@Component
public class SmsChannel implements Channel {
	
	@Value("${twilio.ACCOUNT_SID}")
	private static String account_sid;

	@Value("${twilio.AUTH_TOKEN}")
	private static String auth_token;

	@Autowired
	private SmsValidator smsValidator;

	@Override
	public void notify(com.notification.model.Message msg) {
		if (!smsValidator.isValid(msg.getFrom().toString())) {
			throw new RuntimeException("Invalid email format in - from address");
		}
		if (!smsValidator.isValid(msg.getTo().toString())) {
			throw new RuntimeException("Invalid email format in - to address");
		}
		try {
			MessageCreator message = Message.create(account_sid, new PhoneNumber(msg.getTo()),
					new PhoneNumber(msg.getFrom()), msg.getBody());
		} catch (Exception e) {
			throw new RuntimeException("Failed to send message using sms channel, exception : " + e.getMessage(), e);
		}
	}

	static {
		//Twilio.init(account_sid, auth_token);
	}

	@Override
	public boolean supports(ChannelType channelType) {
		return ChannelType.sms == channelType;
	}

}
