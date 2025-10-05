package com.example.javajpaplayground.domain.mapper;

import com.example.javajpaplayground.domain.dto.ItemResponseDto;
import com.example.javajpaplayground.domain.entity.Item;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemResponseDto toItemResponseDto(Item item);

    Set<ItemResponseDto> toSetOfItemResponseDtos(Set<Item> items);
}
