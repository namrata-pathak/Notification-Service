package com.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.notification.model.ChannelType;
import com.notification.model.Message;

@Service
public class KafkaConsumer {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NotificationService service;
	
	@Autowired
	private KafkaProducer producer;

	@KafkaListener(topics = "${kafka.first.topic}")
	public void processMessage(Message msg) {
		logger.info("Received content = " + msg + " Successfully");

		service.notify(msg,ChannelType.valueOf(msg.getChannelType()));
		
		msg.setStatus("Message sent");
		producer.updateStatus(msg);
	}

}