package com.rizero.testtask.model;

import com.rizero.testtask.dto.CreateClientAccountRequestDTO;
import lombok.Data;

@Data
public class ClientRegistrationInfo {
    String fullName;
    String gender;

    public static ClientRegistrationInfo fromRequestDTO(CreateClientAccountRequestDTO newClientRegistrationInfoDTO) {
        ClientRegistrationInfo clientInfo = new ClientRegistrationInfo();
        clientInfo.setFullName(newClientRegistrationInfoDTO.fullName());
        clientInfo.setGender(newClientRegistrationInfoDTO.gender());
        return clientInfo;
    }
}
