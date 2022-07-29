Synopsis

Generic Notification System

This project demonstrates sample generic service for notification which accepts notification from API and send it to
multiple channels (e.g. sms , email, slack etc ) 

Capabilities supported: • Accept messages including from, to, body and subject • Ability to notify on multiple channels (email, slack, sms) • Deliver messages in correct order • Delete messages automatically after 7 Days

Technologies: • Spring Boot • Jackson • Maven • Swagger • Simpleslackapi • Spring-boot-email-tools • Twilio • Kafka • Log4J • Mockito

To access Rest API: http://localhost:8082/api

API Reference:

URL: http:///notifier/{channelType}/notify This sends the given message to a specified channel like slack or email or sms. Where the channelType is slack/email/sms.

e.g: http://localhost:8082/api/v1.0/notifier/{channelType}/notify

Body: {
"body": "Body of the message",
"from": "sender@gmail.com",
"subject": "Notification Service Test Subject",
"to": "receiver@gmail.com"
}

Settings:

1. Make sure all email and slack properties such as smt details and slack access tokens are configured properly in application.properties.
If Gmail is configured as your mail service then set 'Allow less secure apps = ON' in Sign-in & Security of your google account to send message properly.
2. Make sure Kafka bootstrap server detail is provided.
3. Make sure you create accound id and auth token in twilio tool to use sms channel.
