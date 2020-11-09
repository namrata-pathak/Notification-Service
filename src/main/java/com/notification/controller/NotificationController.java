package com.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.exception.InvalidRequest;
import com.notification.model.ChannelType;
import com.notification.model.Message;
import com.notification.service.KafkaProducer;
import com.notification.service.NotificationService;
import com.notification.util.EmailValidator;
import com.notification.util.SmsValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1.0/notifier")
@Api(value = "Notification APIs")
public class NotificationController {

	@Autowired
	private NotificationService service;

	@Autowired
	EmailValidator emailValidator;

	@Autowired
	SmsValidator smsValidator;

	@Autowired
	private KafkaProducer producer;

	@ApiOperation(value = "Notify the given message to given channelType.")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found") })
	@PostMapping("/notify/{channelType}")
	public Message notify(@PathVariable ChannelType channelType, @RequestBody Message msg) {
		if (ChannelType.email == channelType) {
			if (!emailValidator.isValid(msg.getFrom())) {
				throw new InvalidRequest("From Address", msg.getFrom());
			}
			if (!emailValidator.isValid(msg.getTo())) {
				throw new InvalidRequest("To Address", msg.getFrom());
			}
		}

		else if (ChannelType.sms == channelType) {
			if (!smsValidator.isValid(msg.getFrom())) {
				throw new InvalidRequest("From Phone Number", msg.getFrom());
			}
			if (!smsValidator.isValid(msg.getTo())) {
				throw new InvalidRequest("To Phone Number", msg.getTo());
			}
		}

		service.getNotificationId(msg);
		producer.send(msg);
		msg.setStatus("Message Received");
		return msg;
	}

}
