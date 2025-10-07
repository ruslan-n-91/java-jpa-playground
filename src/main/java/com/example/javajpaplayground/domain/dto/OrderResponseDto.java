package com.example.javajpaplayground.domain.dto;

import java.time.Instant;
import java.util.List;

public record OrderResponseDto(
        Long id,
        String orderNumber,
        String status,
        Instant createdAt,
        List<ItemResponseDto> items,
        ClientResponseDto client,
        DocumentResponseDto document,
        List<WorkerResponseDto> workers,
        List<SpecialDocumentResponseDto> specialDocuments
) {
}
