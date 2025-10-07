package com.example.javajpaplayground.domain.dto;

import java.time.LocalDate;

public record WorkerResponseDto(
        Long id,
        String fistName,
        String secondName,
        String passportNumber,
        LocalDate birthDate
) {
}
