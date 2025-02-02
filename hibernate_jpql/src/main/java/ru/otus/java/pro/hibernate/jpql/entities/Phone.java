package ru.otus.java.pro.hibernate.jpql.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String number;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
