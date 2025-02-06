package ru.otus.java.pro.spring.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.java.pro.spring.app.entities.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepo extends JpaRepository<Account, UUID> {
    Optional<Account> findByIdAndCustomerId(UUID id, String customerId);
    List<Account> findAllByCustomerId(String customerId);
    Optional<Account> findByAccountNumber(String accountNumber);
}
