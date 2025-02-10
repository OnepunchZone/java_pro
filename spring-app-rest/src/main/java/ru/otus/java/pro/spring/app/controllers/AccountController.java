package ru.otus.java.pro.spring.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.java.pro.spring.app.dtos.AccountDto;
import ru.otus.java.pro.spring.app.dtos.AccountLstDto;
import ru.otus.java.pro.spring.app.entities.Account;
import ru.otus.java.pro.spring.app.exceptions_handling.BusinessLogicException;
import ru.otus.java.pro.spring.app.services.AccountService;

import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    private final static Function<Account, AccountDto> ACCOUNT_DTO_FUNCTION = account -> new AccountDto(
        account.getAccountNumber(), account.getCustomerId(), account.getBalance());

    @GetMapping
    public AccountLstDto getAllAccounts(@RequestHeader(name = "customer-id") String customerId) {
        return new AccountLstDto(
                accountService
                        .getAllByCustomerId(customerId)
                        .stream()
                        .map(ACCOUNT_DTO_FUNCTION)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public AccountDto getAccountByID(@RequestHeader(name = "customer-id") String customerId, @PathVariable UUID id) {
        return ACCOUNT_DTO_FUNCTION.apply(
                accountService
                        .getAccountById(id, customerId)
                        .orElseThrow(() -> new BusinessLogicException("Не найден аккаунт")));
    }
}
