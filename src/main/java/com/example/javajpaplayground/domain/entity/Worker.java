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

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "workers_seq_generator"
    )
    @SequenceGenerator(
            name = "workers_seq_generator",
            sequenceName = "workers_id_seq",
            allocationSize = 50
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String fistName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    //@ManyToMany(mappedBy = "workers", fetch = FetchType.EAGER)
    @ManyToMany(mappedBy = "workers")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Order> orders;
}
