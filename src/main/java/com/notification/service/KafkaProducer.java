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

	
	//private KafkaTemplate<String, KafkaUser> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;

	@Value("${kafka.topic}")
	private String kafkaTopic;

	//public void send(KafkaUser customer) {
	//	String key = UUID.randomUUID().toString();
	//	logger.info("sending data=" + customer);
	//	kafkaTemplate.send(kafkaTopic, key, customer);
	//}
	
	public void send(Message msg) {
		String key = UUID.randomUUID().toString();
		logger.info("sending data=" + msg);
		kafkaTemplate.send(kafkaTopic, key, msg);
	}

}
