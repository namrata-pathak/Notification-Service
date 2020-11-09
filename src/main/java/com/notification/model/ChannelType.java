package com.notification.model;

public enum ChannelType {
    slack, email, sms;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}