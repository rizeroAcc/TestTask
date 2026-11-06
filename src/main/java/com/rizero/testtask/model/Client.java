package com.rizero.testtask.model;

import com.rizero.testtask.dto.ClientDTO;
import com.rizero.testtask.entity.ClientEntity;
import lombok.Data;

@Data
public class Client {
    Long id;
    String fullName;
    String gender;
    String status;
    String lastModifyDate;

    public static Client fromDTO(ClientDTO clientDTO) {
        Client client = new Client();
        client.setFullName(clientDTO.fullName());
        client.setGender(clientDTO.gender());
        client.setStatus(clientDTO.status());
        return client;
    }

    public static Client fromEntity(ClientEntity clientEntity) {
        Client client = new Client();
        client.setId(clientEntity.getId());
        client.setFullName(clientEntity.getFullName());
        client.setGender(clientEntity.getGender());
        client.setStatus(clientEntity.getStatus());
        client.setLastModifyDate(clientEntity.getUpdateAt().toString());
        return client;
    }

    public ClientDTO toDTO() {
        return new ClientDTO(
                this.id,
                this.fullName,
                this.gender,
                this.status,
                this.lastModifyDate
        );
    }
}
