package temp;

import com.travelagency.entity.Client;

public interface ClientsService {
    Client getClient(String name);
    void removeClient(Client c);
    boolean addClient(Client c);
}
