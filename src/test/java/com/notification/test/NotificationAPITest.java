
package com.notification.test;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.notification.model.ChannelType;
import com.notification.model.Message;
import com.notification.service.Channel;
import com.notification.service.ChannelFactory;
import com.notification.service.EmailChannel;
import com.notification.service.NotificationService;
import com.notification.service.SlackChannel;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotificationAPITest {
	
	@Mock
	private NotificationService service;
	@Mock
	private ChannelFactory factory;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		List<Channel> channelList = new ArrayList<>(2);
		channelList.add(new SlackChannel());
		channelList.add(new EmailChannel());
		factory = new ChannelFactory(channelList);
		service = new NotificationService(factory);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNotifySlack() {
		Message msg = generateMessage();
		when(service.notify(msg, ChannelType.slack)).thenReturn(anyLong());

		assertEquals(is(anyLong()),service.notify(msg, ChannelType.slack));
	}

	@Test
	public void testNotifyEmail() {
		Message msg = generateMessage();
		when(service.notify(msg, ChannelType.email)).thenReturn(anyLong());

		assertEquals(is(anyLong()), service.notify(msg, ChannelType.email));
	}
	
	@Test
	public void testGetNotificationId() {
		Message msg= generateMessage();
		when(service.getNotificationId(msg)).thenReturn(anyLong());
		assertEquals(is(anyLong()), service.getNotificationId(msg));
		
	}

	@Test(expected = RuntimeException.class)
	public void testEmailMessageInvalid() {
		Message msg = generateMessage();
		msg.setFrom("invalid_mail_format");
		when(service.notify(msg, ChannelType.email)).thenReturn(anyLong());

		assertEquals(is(anyLong()),service.notify(msg, ChannelType.email));
	}

	private Message generateMessage() {
		Message msg = new Message();
		msg.setFrom("sender@gmail.com");
		msg.setTo("receiver@gmail.com");
		msg.setSubject("Test Subject  - Unit Test");
		msg.setBody("Body of Message");
		return msg;
	}
}
