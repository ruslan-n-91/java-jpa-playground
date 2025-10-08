package com.example.javajpaplayground.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.Instant;
import java.util.Set;

@NamedEntityGraph(
        name = "Order.withAllAssociations",
        attributeNodes = {
                @NamedAttributeNode("client"),
                @NamedAttributeNode("document"),
                @NamedAttributeNode(value = "items"),
                @NamedAttributeNode(value = "workers"),
                @NamedAttributeNode(value = "specialDocuments")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orders_seq_generator"
    )
    @SequenceGenerator(
            name = "orders_seq_generator",
            sequenceName = "orders_id_seq",
            allocationSize = 50
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private Instant createdAt;

    //    @OneToOne(fetch = FetchType.EAGER)
    @OneToOne
//    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Document document;

    //    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne
//    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Client client;

    //    @ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany
//    @Fetch(FetchMode.JOIN)
//    @Fetch(FetchMode.SUBSELECT)
//    @BatchSize(size = 50)
    @JoinTable(
            name = "orders_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Item> items;

    //    @ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany
//    @Fetch(FetchMode.JOIN)
//    @Fetch(FetchMode.SUBSELECT)
//    @BatchSize(size = 50)
    @JoinTable(
            name = "orders_workers",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "worker_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Worker> workers;

    //    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "order")
//    @Fetch(FetchMode.JOIN)
//    @Fetch(FetchMode.SUBSELECT)
//    @BatchSize(size = 50)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<SpecialDocument> specialDocuments;
}
