package com.travelagency.service.interfaces;

import com.travelagency.entity.Client;

import java.util.Optional;

public interface ClientService extends UserService<Client> {
    Optional<Client> findByEmail(String email);
}
