package com.rizero.testtask.model;

import com.rizero.testtask.dto.UpdateClientAccountRequestDTO;
import lombok.Data;

@Data
public class ClientInfo {
    String fullName;
    String gender;
    String status;

    public static ClientInfo fromRequestDTO(UpdateClientAccountRequestDTO newClientInfoDTO) {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setFullName(newClientInfoDTO.fullName());
        clientInfo.setGender(newClientInfoDTO.gender());
        clientInfo.setStatus(newClientInfoDTO.status());
        return clientInfo;
    }
}
