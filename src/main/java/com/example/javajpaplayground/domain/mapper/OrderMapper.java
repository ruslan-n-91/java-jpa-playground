package com.example.javajpaplayground.domain.mapper;

import com.example.javajpaplayground.domain.dto.OrderResponseDto;
import com.example.javajpaplayground.domain.entity.Order;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring",
        uses = {ItemMapper.class, ClientMapper.class, DocumentMapper.class, WorkerMapper.class, SpecialDocumentMapper.class})
public interface OrderMapper {

    OrderResponseDto toOrderResponseDto(Order order);

    Set<OrderResponseDto> toSetOfOrderResponseDtos(Set<Order> orders);
}
