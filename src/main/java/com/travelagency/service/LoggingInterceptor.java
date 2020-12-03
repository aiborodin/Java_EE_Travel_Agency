package com.travelagency.service;

import com.travelagency.entity.Identifiable;
import com.travelagency.service.interfaces.annotations.Loggable;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@Loggable
@Priority(Interceptor.Priority.APPLICATION)
public class LoggingInterceptor {

    @PostConstruct
    private void init(InvocationContext ic) throws Exception {
        try {
            ic.proceed();
            final Logger logger = ((AbstractEntityService<? extends Identifiable>)ic.getTarget()).getLogger();
            logger.fine("Created bean: " + ic.getTarget().getClass().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        final Logger logger = ((AbstractEntityService<? extends Identifiable>)ic.getTarget()).getLogger();
        logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
        try {
            return ic.proceed();
        } finally {
            logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
        }
    }


}
