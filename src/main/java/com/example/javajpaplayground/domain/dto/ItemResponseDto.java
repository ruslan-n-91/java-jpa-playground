package com.example.javajpaplayground.domain.dto;

public record ItemResponseDto(
        Long id,
        String itemNumber,
        String name,
        String description
) {
}
