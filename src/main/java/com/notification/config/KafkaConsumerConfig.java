package com.notification.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.ResourceUtils;

import com.notification.model.Message;
import com.notification.service.KafkaConfig;
import com.notification.service.KafkaUser;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private KafkaConfig config;

	@Bean
	public ConsumerFactory<String, Message> consumerFactory() {

		File jaasFile = null;
		logger.info("Processing Kafka Consumer =======>");
		try {

			jaasFile = ResourceUtils.getFile(config.getJaasProducerConfig());
		} catch (FileNotFoundException e) {
			logger.error("Error in reading file path " + e.getMessage());
		}

		System.setProperty("java.security.auth.login.config", jaasFile.getPath());
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServer());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, config.getConsumerGroup());
		props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
		props.put("sasl.mechanism", "SCRAM-SHA-256");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
		props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "50000");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
				new JsonDeserializer<>(Message.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
