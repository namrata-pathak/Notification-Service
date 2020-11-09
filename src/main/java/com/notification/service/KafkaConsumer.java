package com.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.notification.model.Message;

@Service
public class KafkaConsumer {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@KafkaListener(topics = "${kafka.topic}")
	public void processMessage(Message msg) {
		logger.info("Received content = " + msg + " Successfully");

	}
}