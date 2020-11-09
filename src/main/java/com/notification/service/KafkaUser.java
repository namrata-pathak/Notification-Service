package com.notification.service;

import java.util.Date;

import com.notification.model.ChannelType;
import com.notification.model.Message;

public class KafkaUser {
	private String from;
	private String to;
	private String body;
	private String subject;
	private int id;
	private Date date;
	private int partition;
	private ChannelType channelType;
	private Message msg;
	private long notificationId;
	
	public long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public ChannelType getChannelType() {
		return channelType;
	}

	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}

	public int getPartition() {
		return partition;
	}

	public void setPartition(int partition) {
		this.partition = partition;
	}

	public int getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public KafkaUser() {
		super();
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		this.date = date;
		// TODO Auto-generated constructor stub
	}

	public void setId(int id) {
		this.id = id;
	}

	public KafkaUser(String name, int id, String message) {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		this.date = date;
		this.id = id;
		this.body = body;
		this.subject = subject;
		this.from = from;
		this.to = to;
	}

	/*
	 * public KafkaUser(String name, int id) { this.name = name; this.id = id; }
	 */

	public KafkaUser(String subject, String to, String from, String body, int partition, int id,
			String message) {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		this.date = date;
		this.id = id;
		this.body = body;
		this.to = to;
		this.subject = subject;
		this.from= from;
		this.partition = partition;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString() {
		String info = String.format("{ 'subject': %s, 'to': %s, 'from': %s, 'partition': %d, 'id': %d}",
				subject, to, from, partition, id);
		return info;
	}
}
