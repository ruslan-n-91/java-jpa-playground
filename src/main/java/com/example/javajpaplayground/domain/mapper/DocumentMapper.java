package com.example.javajpaplayground.domain.mapper;

import com.example.javajpaplayground.domain.dto.DocumentResponseDto;
import com.example.javajpaplayground.domain.entity.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentResponseDto toDocumentResponseDto(Document document);
}
