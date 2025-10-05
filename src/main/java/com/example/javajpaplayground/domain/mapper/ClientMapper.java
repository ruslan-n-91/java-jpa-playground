package com.example.javajpaplayground.domain.mapper;

import com.example.javajpaplayground.domain.dto.ClientResponseDto;
import com.example.javajpaplayground.domain.entity.Client;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponseDto toClientResponseDto(Client client);

    Set<ClientResponseDto> toSetOfClientResponseDtos(Set<Client> clients);
}
