package com.example.javajpaplayground.repository;

import com.example.javajpaplayground.domain.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o " +
           "LEFT JOIN FETCH o.client " +
           "LEFT JOIN FETCH o.document " +
           "LEFT JOIN FETCH o.specialDocuments " +
           "LEFT JOIN FETCH o.workers " +
           "LEFT JOIN FETCH o.items")
    /*
    При одновременной загрузке нескольких коллекций (например, @OneToMany или @ManyToMany)
    в одном JPQL-запросе Hibernate выбрасывает org.hibernate.loader.MultipleBagFetchException.
    Также стоит не забывать про Cartesian Product Problem.
     */
    List<Order> findAllOrdersJPQL();

    @EntityGraph(value = "Order.withAllAssociations", type = EntityGraph.EntityGraphType.FETCH)
//    @EntityGraph(attributePaths = {"client", "document", "items", "workers", "specialDocuments"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT o FROM Order o")
    List<Order> findAllOrdersEntityGraph();
}
