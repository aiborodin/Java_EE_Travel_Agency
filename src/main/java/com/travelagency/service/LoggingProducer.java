package com.travelagency.service;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import java.util.logging.*;

public class LoggingProducer {

    private FileHandler fh;

    public LoggingProducer() {
        try {
            fh = new FileHandler("./Beans.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Для каждого бина можно создать свой Logger с помощью injection-метода в бине
    @Produces
    private Logger createLogger(InjectionPoint injectionPoint) {
        Logger logger = Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
        logger.setLevel(Level.ALL);
        logger.addHandler(fh);
        return logger;
    }

    private void closeHandlers(@Disposes Logger logger) {
        for (Handler handler : logger.getHandlers()) {
            handler.close();
        }
    }
}
