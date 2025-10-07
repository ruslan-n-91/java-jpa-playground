package com.example.javajpaplayground.domain.mapper;

import com.example.javajpaplayground.domain.dto.WorkerResponseDto;
import com.example.javajpaplayground.domain.entity.Worker;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface WorkerMapper {

    WorkerResponseDto toWorkerResponseDto(Worker worker);

    Set<WorkerResponseDto> toSetOfWorkerResponseDtos(Set<Worker> workers);
}
