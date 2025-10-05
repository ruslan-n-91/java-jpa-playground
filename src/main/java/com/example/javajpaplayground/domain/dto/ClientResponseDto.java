package com.example.javajpaplayground.domain.dto;

import java.time.LocalDate;

public record ClientResponseDto(
        Long id,
        String fistName,
        String secondName,
        String passportNumber,
        LocalDate birthDate
) {
}
