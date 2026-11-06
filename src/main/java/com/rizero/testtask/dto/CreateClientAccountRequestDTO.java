package com.rizero.testtask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateClientAccountRequestDTO(
        @NotBlank String fullName,
        @Size(max = 1) String gender
) { }
