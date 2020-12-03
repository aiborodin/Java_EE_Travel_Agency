package temp.demo;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.logging.Logger;

@Loggable
public class CustomerService {
    @Inject
    private EntityManager em;
    @Inject
    Conversation conversation;

    @Inject @InjectTest
    Logger logger;

    public void createCustomer(Customer customer) {
        em.persist(customer);
    }
    public Customer findCustomerById(Long id) {
        return em.find(Customer.class, id);
    }
}
