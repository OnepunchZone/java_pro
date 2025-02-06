package ru.otus.java.pro.spring.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "transfers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "target_client_id")
    private String targetClientId;

    @Column(name = "source_account")
    private String sourceAccount;

    @Column(name = "target_account")
    private String targetAccount;

    @Column(name = "message")
    private String message;

    @Column(name = "amount")
    private int amount;

    public Transfer(Long id, Long id1, int amount) {
    }
}
