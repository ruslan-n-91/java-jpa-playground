package com.example.javajpaplayground.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "documents_seq_generator"
    )
    @SequenceGenerator(
            name = "documents_seq_generator",
            sequenceName = "documents_id_seq",
            allocationSize = 50
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "created_at")
    private Instant createdAt;

    @OneToOne(mappedBy = "document")
//    @OneToOne(mappedBy = "document", fetch = FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Order order;
}
