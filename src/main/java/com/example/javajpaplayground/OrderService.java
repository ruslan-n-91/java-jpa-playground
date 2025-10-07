package com.example.javajpaplayground;

import com.example.javajpaplayground.domain.dto.OrderResponseDto;
import com.example.javajpaplayground.domain.entity.Order;
import com.example.javajpaplayground.domain.mapper.OrderMapper;
import com.example.javajpaplayground.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public List<OrderResponseDto> findAll() {
//        List<Order> orders = orderRepository.findAllOrdersJPQL();
        List<Order> orders = orderRepository.findAllOrdersEntityGraph();
//        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toOrderResponseDto)
                .toList();
    }
}
