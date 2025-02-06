package ru.otus.java.pro.spring.app.dtos;

import java.util.List;

public record AccountDto(String accountNumber, String customerId, int balance) {

}
