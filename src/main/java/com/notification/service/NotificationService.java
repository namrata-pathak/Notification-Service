package com.notification.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.model.ChannelType;
import com.notification.model.Message;

@Service
public class NotificationService {

	private static final Logger LOG = LogManager.getLogger(NotificationService.class);

	@Autowired
	ChannelFactory factory;

	public NotificationService(ChannelFactory factory) {
		this.factory = factory;
	}

	private AtomicInteger notificationId = new AtomicInteger(1);


	/**
	 * Notifies configured channels(like slack and email and sms) with the given
	 * message.
	 * 
	 * @param channelType Type of channel to notify - slack, email, sms 
	 * @param msg         The message includes from, to, subject, body
	 */	
	public long notify(Message msg,ChannelType channelType) {
		
		msg.setId(notificationId.getAndIncrement());
		factory.get(channelType).notify(msg);
		LOG.debug("ID = " + notificationId + ", Message sent = " + msg);
		return notificationId.longValue();
	}

	public long getNotificationId(Message msg) {
		msg.setId(notificationId.getAndIncrement());
		
		LOG.debug("ID = " + notificationId + ", Message sent = " + msg);

		return notificationId.longValue();
	}
}
