package com.rizero.testtask.service;

import com.rizero.testtask.dto.ClientDTO;
import com.rizero.testtask.dto.CreateClientAccountRequestDTO;
import com.rizero.testtask.dto.UpdateClientAccountRequestDTO;
import com.rizero.testtask.entity.ClientEntity;
import com.rizero.testtask.model.Client;
import com.rizero.testtask.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    public Client createUserAccount(CreateClientAccountRequestDTO requestDTO){
        ClientEntity newClient = new ClientEntity();
        newClient.setFullName(requestDTO.fullName());
        newClient.setGender(requestDTO.gender());
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
    public Optional<Client> updateUserAccount(Long id, UpdateClientAccountRequestDTO requestDTO) {
        var client = clientRepository.findById(id);
        if (client.isEmpty()) return Optional.empty();

        var clientEntity = client.get();
        if (requestDTO.fullName() != null) { clientEntity.setFullName(requestDTO.fullName()); }
        if (requestDTO.gender() != null) { clientEntity.setGender(requestDTO.gender()); }
        if (requestDTO.status() != null) { clientEntity.setStatus(requestDTO.status()); }
        return Optional.of(
                Client.fromEntity(clientRepository.save(clientEntity))
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
