package com.notification.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.notification.model.ChannelType;
import com.notification.model.Message;

import java.util.List;

@Component
public class ChannelFactory {
    private final List<Channel> channelList;

    @Autowired
    public ChannelFactory(List<Channel> channelList) {
        this.channelList = channelList;
    }

    public Channel get(ChannelType c) {
        return channelList
                .stream()
                .filter(service -> service.supports(c))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No channel found with type : "+c));
    }

    public void notifyAll(Message msg) {
        for(Channel c : channelList) {
            c.notify(msg);
        }
    }

    public List<Channel> getChannels() {
        return channelList;
    }
}
