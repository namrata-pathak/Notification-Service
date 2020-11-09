package com.notification.service;

import com.notification.model.ChannelType;
import com.notification.model.Message;

public interface Channel {
    default void notify(Message msg) {
        throw new RuntimeException("Notify method is not implemented yet.");
    }

    default boolean supports(ChannelType type) {
        throw new RuntimeException("Notify method is not implemented yet.");
    }
}
