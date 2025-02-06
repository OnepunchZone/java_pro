package ru.otus.java.pro.spring.app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.java.pro.spring.app.entities.Account;
import ru.otus.java.pro.spring.app.repositories.AccountRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepo accountRepo;

    public Optional<Account> getAccountById(UUID id, String customerId) {
        return accountRepo.findByIdAndCustomerId(id, customerId);
    }

    public List<Account> getAllByCustomerId(String customerId) {
        return accountRepo.findAllByCustomerId(customerId);
    }
}
