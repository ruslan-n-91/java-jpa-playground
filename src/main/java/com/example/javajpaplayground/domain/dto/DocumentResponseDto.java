package com.example.javajpaplayground.domain.dto;

import java.time.Instant;

public record DocumentResponseDto(
        Long id,
        String documentNumber,
        String documentType,
        Instant createdAt
) {
}
