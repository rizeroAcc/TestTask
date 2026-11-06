package com.rizero.testtask.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateClientAccountRequestDTO(
        @Pattern(regexp = ".*\\S.*") String fullName,
        @Size(max = 1) String gender,
        String status
) {
}
