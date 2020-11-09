package com.notification.service;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class CustomPartitioner implements Partitioner{

	  private static final int PARTITION_COUNT=50;

	  @Override
	  public void configure(Map<String, ?> configs) {

	  }

	  @Override
	  public void close() {

	  }

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		// TODO Auto-generated method stub
		return 0;
	}

	}