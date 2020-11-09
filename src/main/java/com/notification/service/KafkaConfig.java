package com.notification.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class KafkaConfig {
	
	
	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServer;
	
	//@Value("${kafka.certificate.keystore.path}")
	private String keyStorePath;
	
	
	@Value("${kafka.jaas.producer.config}")
	private String jaasProducerConfig;

	@Value("${kafka.consumer.group-id}")
	private String consumerGroup;

	@Profile("${spring.profiles.active}")
	public String getBootstrapServer() {
		return bootstrapServer;
	}


	
	public void setBootstrapServer(String bootstrapServer) {
		this.bootstrapServer = bootstrapServer;
	}


	@Profile("${spring.profiles.active}")
	//@Bean
	public String getKeyStorePath() {
		return keyStorePath;
	}


	public void setKeyStorePath(String keyStorePath) {
		this.keyStorePath = keyStorePath;
	}


	@Profile("${spring.profiles.active}")
	//@Bean
	public String getJaasProducerConfig() {
		return jaasProducerConfig;
	}


	public void setJaasProducerConfig(String jaasProducerConfig) {
		this.jaasProducerConfig = jaasProducerConfig;
	}



	public String getConsumerGroup() {
		return consumerGroup;
	}



	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}



	@Override
	public String toString() {
		return "KafkaConfig [bootstrapServer=" + bootstrapServer + ", keyStorePath=" + keyStorePath
				+ ", jaasProducerConfig=" + jaasProducerConfig + "]";
	}
	
	/*@Value("${certificate.truststore.path}")
	private String truststore;*/
	

}

