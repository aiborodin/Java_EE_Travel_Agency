package com.travelagency.ejb;

import com.travelagency.entity.Agent;
import com.travelagency.entity.Client;
import com.travelagency.entity.TravelOffer;
import com.travelagency.service.interfaces.AgentService;
import com.travelagency.service.interfaces.ClientService;
import com.travelagency.service.interfaces.TravelOfferService;

import javax.annotation.Resource;
import javax.ejb.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Stateful
@StatefulTimeout(value = 20, unit = TimeUnit.SECONDS)
public class TravelGroupEJB {

    private final List<Client> group;

    private TravelOffer travelOffer;

    private Agent agent;

    @EJB
    private ClientService clientService;

    @EJB
    private AgentService agentService;

    @EJB
    private TravelOfferService travelOfferService;

    @Resource
    SessionContext sessionContext;

    public TravelGroupEJB() {
        group = new ArrayList<>();
    }

    public void addMember(int clientId) {
        if (group.stream().noneMatch(client -> client.getId() == clientId)) {
            Optional<Client> client = clientService.find(clientId);
            if (client.isPresent()) {
                group.add(client.get());
            } else {
                throw new IllegalArgumentException("Client with id: " + client + " not found.");
            }
        }
    }

    public void removeMember(Client client) {
        group.remove(client);
    }

    @Asynchronous
    public void notifyMembers() {
        // Simulate long email sending task
        group.forEach(member -> System.out.println("Send notification to: " + member.getEmail()));
    }

    @Asynchronous
    public Future<Double> calculateAverageGroupAge() {
        if (sessionContext.wasCancelCalled()) {
            return new AsyncResult<>(-1.);
        } else {
            OptionalDouble averageAge = group.stream().mapToInt(Client::getAge).average();
            if (averageAge.isPresent()) {
                return new AsyncResult<>(averageAge.getAsDouble());
            } else {
                return new AsyncResult<>(0.);
            }
        }
    }

    public Stream<Client> getAllMembers() {
        return group.stream();
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(int agentId) {
        Optional<Agent> agent = agentService.find(agentId);
        if (agent.isPresent()) {
            this.agent = agent.get();
        } else {
            throw new IllegalArgumentException("Agent with id: " + agentId + " not found.");
        }
    }

    public TravelOffer getTravelOffer() {
        return travelOffer;
    }

    public void setTravelOffer(int offerId) {
        Optional<TravelOffer> travelOffer = travelOfferService.find(offerId);
        if (travelOffer.isPresent()) {
            this.travelOffer = travelOffer.get();
        } else {
            throw new IllegalArgumentException("Travel Offer with id: " + offerId + " not found.");
        }
    }

    @Remove
    public void checkOut() {
        System.out.println("Creating new travel group");
        System.out.println("Travel offer: " + travelOffer.getName());
        System.out.println("Members: ");
        group.forEach(member -> System.out.println(member.getFirstName() + " " + member.getLastName()));
        System.out.println("Agent: " + agent.getFirstName() + " " + agent.getLastName());
        group.clear();
    }
}
