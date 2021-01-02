package com.kdmins.common.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    java.util.Collection Collection;
    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");
        //TODO process data
    }
}
