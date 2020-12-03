package temp;

import com.travelagency.entity.Client;

import javax.enterprise.event.Observes;

public class ClientServiceObserver {
    public void registerClient(@Observes Client c) {
        System.out.println("org.travelagency.entity.Client: " + c.getName() + " is registered!");
    }
}
