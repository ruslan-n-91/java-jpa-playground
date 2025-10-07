package com.example.javajpaplayground;

import com.example.javajpaplayground.domain.Filter;
import com.example.javajpaplayground.domain.dto.OrderResponseDto;
import com.example.javajpaplayground.domain.entity.Order;
import com.example.javajpaplayground.domain.mapper.OrderMapper;
import com.example.javajpaplayground.repository.OrderRepository;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @PersistenceContext
    private EntityManager entityManager;

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final List<Filter> filters;

    @Transactional
    public List<OrderResponseDto> findAll() {
//        List<Order> orders = orderRepository.findAllOrdersJPQL();
        List<Order> orders = orderRepository.findAllOrdersEntityGraph();
//        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toOrderResponseDto)
                .toList();
    }

    @Transactional
    public List<OrderResponseDto> findAllFiltered() {
        System.out.println("FILTERS " + filters);
        String queryString = "SELECT o FROM Order o";

//        String filtersString = filters.stream()
//                .map(filter -> "o." + filter.field() + " = " + filter.value() + "")
//                .collect(Collectors.joining(" AND ", " WHERE ", ""));
//        query = query + filtersString;
//        System.out.println("QUERY " + query);
//
//        List<Order> orders = entityManager.createQuery(query, Order.class).getResultList();

        Map<String, Object> parameters = new HashMap<>();
        if (!filters.isEmpty()) {
            List<String> conditions = new ArrayList<>();
            for (int i = 0; i < filters.size(); i++) {
                Filter filter = filters.get(i);
                conditions.add("o." + filter.field() + " = :param" + i);
                parameters.put("param" + i, filter.value());
            }
            queryString += " WHERE " + String.join(" AND ", conditions);
        }
        System.out.println("QUERY " + queryString);

        TypedQuery<Order> query = entityManager.createQuery(queryString, Order.class);
//        parameters.forEach(query::setParameter);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

//        EntityGraph<?> orderGraph = entityManager.getEntityGraph("Order.withAllAssociations");
        EntityGraph<Order> orderGraph = entityManager.createEntityGraph(Order.class);
        orderGraph.addAttributeNodes("client");
        orderGraph.addAttributeNodes("document");
        orderGraph.addAttributeNodes("items");
        orderGraph.addAttributeNodes("workers");
        orderGraph.addAttributeNodes("specialDocuments");

//        query.setHint("jakarta.persistence.loadgraph", orderGraph);
        query.setHint("jakarta.persistence.fetchgraph", orderGraph);

        List<Order> orders = query.getResultList();

        return orders.stream()
                .map(orderMapper::toOrderResponseDto)
                .toList();
    }
}
