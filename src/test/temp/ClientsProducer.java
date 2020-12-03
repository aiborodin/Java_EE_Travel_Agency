package temp;

import com.travelagency.entity.Client;

import javax.enterprise.inject.Produces;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientsProducer {
    @Produces @DefaultClients
    ArrayList<Client> getClients() {
        return new ArrayList<>(Arrays.asList(
                new Client("Bob", "Schmeling", 30),
                new Client("Vasya", "Dickens", 23)
        ));
    }
}
