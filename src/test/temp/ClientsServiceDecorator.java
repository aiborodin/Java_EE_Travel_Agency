package temp;

import com.travelagency.entity.Client;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public abstract class ClientsServiceDecorator implements ClientsService{

    @Inject @Delegate
    ClientsService clientsService;

    @Override
    public Client getClient(String name) {
        Client c = clientsService.getClient(name);
        c.setName(c.getName() + " decorated");
        return c;
    }

    @Override
    public void removeClient(Client c) {

    }
}
