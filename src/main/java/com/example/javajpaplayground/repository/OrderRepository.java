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
    ??? ВСЕ НОРМ РАБОТАЕТ
    При одновременной загрузке нескольких коллекций (например, @OneToMany или @ManyToMany)
    в одном JPQL-запросе Hibernate выбрасывает org.hibernate.loader.MultipleBagFetchException.
    Поскольку поле items является коллекцией (@ManyToMany), а @OneToMany-коллекций в Order нет,
    в данном конкретном случае запрос будет работать. Однако если добавить еще одну коллекцию в Order
    и попытаться загрузить ее в этом же запросе, возникнет ошибка.
    Чтобы это исправить можно использовать Entity Graph.
    ??? ВСЕ НОРМ РАБОТАЕТ
     */
    List<Order> findAllOrdersJPQL();

    @EntityGraph(value = "Order.withAllAssociations", type = EntityGraph.EntityGraphType.FETCH)
//    @EntityGraph(attributePaths = {"client", "document", "items", "workers", "specialDocuments"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT o FROM Order o")
    List<Order> findAllOrdersEntityGraph();
}
