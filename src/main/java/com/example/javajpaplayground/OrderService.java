package com.example.javajpaplayground;

import com.example.javajpaplayground.domain.Filter;
import com.example.javajpaplayground.domain.dto.OrderResponseDto;
import com.example.javajpaplayground.domain.entity.Order;
import com.example.javajpaplayground.domain.mapper.OrderMapper;
import com.example.javajpaplayground.repository.OrderRepository;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
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
    //@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    /// ПРИ readonly = true ИЛИ isolation = НЕ РАВНЫЙ Isolation.DEFAULT ORM ВОЗЬМЕТ СОЕДИНЕНИЕ ИЗ ПУЛА
    /// ПРЯМО В НАЧАЛЕ РАБОТЫ МЕТОДА ДО ОБРАЩЕНИЙ К БД, А НЕ КАК ОБЫЧНО ТОЛЬКО ПРИ ПЕРВОМ ОБРАЩЕНИИ
    public List<OrderResponseDto> findAll() {
//        List<Order> orders = orderRepository.findAllOrdersJPQL();
//        List<Order> orders = orderRepository.findAllOrdersEntityGraph();
        List<Order> orders = orderRepository.findAll();
//        List<Order> orders = orderRepository.findAllOrdersEntityGraphToOneFields();
//        List<Order> orders = List.of(orderRepository.findById(1L).get());

        /// ///// CARTESIAN PRODUCT PROBLEM TEST
        String sql = "select\n" +
                     "        o1_0.id,\n" +
                     "        c1_0.id,\n" +
                     "        c1_0.birth_date,\n" +
                     "        c1_0.first_name,\n" +
                     "        c1_0.passport_number,\n" +
                     "        c1_0.second_name,\n" +
                     "        o1_0.created_at,\n" +
                     "        d1_0.id,\n" +
                     "        d1_0.created_at,\n" +
                     "        d1_0.document_number,\n" +
                     "        d1_0.document_type,\n" +
                     "        i1_0.order_id,\n" +
                     "        i1_1.id,\n" +
                     "        i1_1.description,\n" +
                     "        i1_1.item_number,\n" +
                     "        i1_1.name,\n" +
                     "        o1_0.order_number,\n" +
                     "        sd1_0.order_id,\n" +
                     "        sd1_0.id,\n" +
                     "        sd1_0.created_at,\n" +
                     "        sd1_0.document_number,\n" +
                     "        sd1_0.document_type,\n" +
                     "        o1_0.status,\n" +
                     "        w1_0.order_id,\n" +
                     "        w1_1.id,\n" +
                     "        w1_1.birth_date,\n" +
                     "        w1_1.first_name,\n" +
                     "        w1_1.passport_number,\n" +
                     "        w1_1.second_name \n" +
                     "    from\n" +
                     "        orders o1_0 \n" +
                     "    left join\n" +
                     "        clients c1_0 \n" +
                     "            on c1_0.id=o1_0.client_id \n" +
                     "    left join\n" +
                     "        documents d1_0 \n" +
                     "            on d1_0.id=o1_0.document_id \n" +
                     "    left join\n" +
                     "        orders_items i1_0 \n" +
                     "            on o1_0.id=i1_0.order_id \n" +
                     "    left join\n" +
                     "        items i1_1 \n" +
                     "            on i1_1.id=i1_0.item_id \n" +
                     "    left join\n" +
                     "        special_documents sd1_0 \n" +
                     "            on o1_0.id=sd1_0.order_id \n" +
                     "    left join\n" +
                     "        orders_workers w1_0 \n" +
                     "            on o1_0.id=w1_0.order_id \n" +
                     "    left join\n" +
                     "        workers w1_1 \n" +
                     "            on w1_1.id=w1_0.worker_id";
        Query query = entityManager.createNativeQuery(sql, Tuple.class);
        List<Tuple> list = query.getResultList();
        System.out.println("LIST TUPLES " + list);
        list.forEach(tuple -> System.out.println("tuple " + tuple));
        /// ///// CARTESIAN PRODUCT PROBLEM TEST

        System.out.println("MAPPERS AFTER THIS");

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
