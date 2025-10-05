package com.example.javajpaplayground.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "items_seq_generator"
    )
    @SequenceGenerator(
            name = "items_seq_generator",
            sequenceName = "items_id_seq",
            allocationSize = 50
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "item_number")
    private String itemNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    //@ManyToMany(mappedBy = "items", fetch = FetchType.EAGER)
    @ManyToMany(mappedBy = "items")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Order> orders;
}
