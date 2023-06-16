package ru.otus.services;

import org.springframework.stereotype.Service;
import ru.otus.model.Client;
import ru.otus.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBServiceClientImpl implements DBServiceClient {
    private final ClientRepository repository;

    public DBServiceClientImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client saveClient(Client client) {
        return repository.save(client);
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        repository.findAll().forEach(clients::add);
        return clients;
    }

}
