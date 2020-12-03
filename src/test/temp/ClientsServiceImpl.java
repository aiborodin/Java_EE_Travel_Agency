package temp;

import com.travelagency.entity.Client;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.ArrayList;

public class ClientsServiceImpl implements ClientsService{

    @Inject @DefaultClients
    private ArrayList<Client> clients;

    @Inject
    Event<Client> clientAddedEvent;

    @Override
    public Client getClient(String name) {
        return clients.stream().filter(c -> c.getName().equals(name)).findFirst().get();
    }

    @Override
    public void removeClient(Client c) {
    }

    @Override
    public boolean addClient(Client c) {
        clients.add(c);
        clientAddedEvent.fire(c);
        return true;
    }
}
