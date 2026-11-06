package com.rizero.testtask.service;

import com.rizero.testtask.entity.ClientEntity;
import com.rizero.testtask.model.Client;
import com.rizero.testtask.model.ClientInfo;
import com.rizero.testtask.model.ClientRegistrationInfo;
import com.rizero.testtask.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createUserAccount(ClientRegistrationInfo clientRegistrationInfo){
        ClientEntity newClient = new ClientEntity();
        newClient.setFullName(clientRegistrationInfo.getFullName());
        newClient.setGender(clientRegistrationInfo.getGender());
        newClient.setStatus("ACTIVE");
        newClient = clientRepository.save(newClient);
        return Client.fromEntity(newClient);
    }

    public List<Client> getAllUsersAccount(){
        List<ClientEntity> clientEntities = clientRepository.findAll();
        return clientEntities.stream().map(Client::fromEntity).toList();
    }

    public Optional<Client> getUserAccount(Long id){
        return clientRepository.findById(id).map(Client::fromEntity);
    }

    @Transactional
    public Optional<Client> updateUserAccount(Long id, ClientInfo newClientInfo) {
        var client = clientRepository.findById(id);
        if (client.isEmpty()) return Optional.empty();

        var clientEntity = client.get();
        if (newClientInfo.getFullName() != null) { clientEntity.setFullName(newClientInfo.getFullName()); }
        if (newClientInfo.getGender() != null) { clientEntity.setGender(newClientInfo.getGender()); }
        if (newClientInfo.getStatus() != null) { clientEntity.setStatus(newClientInfo.getStatus()); }
        return Optional.of(
                Client.fromEntity(clientRepository.saveAndFlush(clientEntity))
        );
    }

    @Transactional
    public boolean deleteUserAccount(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
