package com.example.javajpaplayground.domain.mapper;

import com.example.javajpaplayground.domain.dto.SpecialDocumentResponseDto;
import com.example.javajpaplayground.domain.entity.SpecialDocument;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SpecialDocumentMapper {

    SpecialDocumentResponseDto toSpecialDocumentResponseDto(SpecialDocument specialDocument);

    Set<SpecialDocumentResponseDto> toSpecialDocumentResponseDtos(Set<SpecialDocument> specialDocuments);
}
