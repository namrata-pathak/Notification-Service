package com.notification.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.notification.model.Message;

@Service
public class KafkaProducer {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;

	@Value("${kafka.first.topic}")
	private String kafkaFirstTopic;
	
	@Value("${kafka.second.topic}")
	private String kafkaSecondTopic;

	String key = UUID.randomUUID().toString();
	public void send(Message msg) {
		
		logger.info("sending data=" + msg);
		kafkaTemplate.send(kafkaFirstTopic, key, msg);
	}
	
	public void updateStatus(Message msg) {
		logger.info("sending data=" + msg);
		kafkaTemplate.send(kafkaSecondTopic, key, msg);
	}

}
