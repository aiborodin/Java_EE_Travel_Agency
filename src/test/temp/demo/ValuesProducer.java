package temp.demo;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Logger;

public class ValuesProducer implements Serializable {
    @Produces @InjectTest
    private String name = "Alex";

    @Produces
    private int num = 100;

    @Produces
    private double getSkill() {
        return 1.1;
    }

    @Produces @InjectTest
    Logger getLogger(InjectionPoint ip) {
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
    }
}
