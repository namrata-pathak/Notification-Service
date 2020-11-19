package com.notification.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.ResourceUtils;

import com.notification.model.Message;
import com.notification.service.CustomPartitioner;
import com.notification.service.KafkaConfig;

@Configuration
public class KafkaProducerConfig {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private KafkaConfig config;

	/**
	 * @return
	 */

	@Bean
	public ProducerFactory<String, Message> producerFactory() {
		File jaasFile = null;
		logger.info("Processing Kafka Producer =======>");
		try {

			//ResourceUtils.getFile(config.getKeyStorePath());
			jaasFile = ResourceUtils.getFile(config.getJaasProducerConfig());
		} catch (FileNotFoundException e) {
			logger.error("Error in reading file path " + e.getMessage());
		}
		logger.info("Jaas File path: " + jaasFile); //
		System.setProperty("java.security.auth.login.config", jaasFile.getPath());
		//System.setProperty("com.sun.jndi.ldap.object.disableEndpointIdentification", "true");
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServer());
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		// configProps.put(TopicConfig.DELETE_RETENTION_MS_CONFIG, 7);
		configProps.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
		configProps.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
		configProps.put("sasl.mechanism", "SCRAM-SHA-256");

		configProps.put(ProducerConfig.ACKS_CONFIG, "all");
		configProps.put(ProducerConfig.RETRIES_CONFIG, 0);

		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, Message> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

}
