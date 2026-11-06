package com.rizero.testtask.controller;

import com.rizero.testtask.dto.CreateClientAccountRequestDTO;
import com.rizero.testtask.dto.ClientDTO;
import com.rizero.testtask.dto.UpdateClientAccountRequestDTO;
import com.rizero.testtask.model.Client;
import com.rizero.testtask.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ClientAccountController {
    @Autowired
    private ClientService clientService;
    @PostMapping("/clients")
    public ClientDTO createClientAccount(@RequestBody @Valid CreateClientAccountRequestDTO createClientAccountRequestDTO) {
        return clientService.createUserAccount(createClientAccountRequestDTO).toDTO();
    }
    @GetMapping("/clients")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllUsersAccount().stream().map(Client::toDTO).toList();
    }
    @GetMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        Optional<Client> result = clientService.getUserAccount(id);
        if (result.isPresent()){
            return result.get().toDTO();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/clients/{id}")
    public ClientDTO updateClientAccount(@RequestBody @Valid UpdateClientAccountRequestDTO clientDTO, @PathVariable Long id) {
        Optional<Client> result = clientService.updateUserAccount(id,clientDTO);
        if (result.isPresent()){
            return result.get().toDTO();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/clients/{id}")
    public void deleteClientAccount(@PathVariable Long id) {
        if (!clientService.deleteUserAccount(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
