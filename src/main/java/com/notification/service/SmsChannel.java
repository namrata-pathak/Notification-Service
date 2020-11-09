package com.notification.service;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.notification.model.ChannelType;
import com.notification.util.SmsValidator;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.creator.api.v2010.account.MessageCreator;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.type.PhoneNumber;

@Component
public class SmsChannel implements Channel {
	public static final String ACCOUNT_SID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	public static final String AUTH_TOKEN = "your_auth_token";

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
			MessageCreator message = Message.create(ACCOUNT_SID, new PhoneNumber(msg.getTo()),
					new PhoneNumber(msg.getFrom()), msg.getBody());
		} catch (Exception e) {
			throw new RuntimeException("Failed to send message using sms channel, exception : " + e.getMessage(), e);
		}
	}

	static {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}

    @Override
    public boolean supports(ChannelType channelType) {
        return ChannelType.sms == channelType;
    }
	
}
