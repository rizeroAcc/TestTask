package com.rizero.testtask.dto;

public record ClientDTO(
        Long id,
        String fullName,
        String gender,
        String status,
        String lastModifyDate
) {}
