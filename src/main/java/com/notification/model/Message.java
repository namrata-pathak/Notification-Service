package com.notification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

	private String subject;
	private String from;
	private String to;
	private String body;
	@ApiModelProperty(hidden = true)
	private long id;
	@ApiModelProperty(hidden = true)
	private String sentTime;
	@ApiModelProperty(hidden = true)
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Message() {
		this.sentTime = LocalDateTime.now().toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public String getSentTime() {
		return sentTime;
	}

	public void setSentTime(String sentTime) {
		this.sentTime = sentTime;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", subject=" + subject + ", from=" + from + ", to=" + to + ", body=" + body
				+ ", sentTime=" + sentTime + ", status=" + status + "]";
	}
}
