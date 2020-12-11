package com.travelagency.service;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import java.util.logging.*;

public class LoggingProducer {

    @Produces
    private Logger createLogger(InjectionPoint injectionPoint) {
        Logger logger = Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
//        Logger logger = Logger.getLogger(injectionPoint.getBean().getBeanClass().getName());
        try {
            final FileHandler fh = new FileHandler("./Beans.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
        return logger;
    }

    private void closeHandlers(@Disposes Logger logger) {
        for (Handler handler : logger.getHandlers()) {
            handler.close();
        }
    }
}
