package ru.otus.java.pro.spring.app.dtos;

import java.util.UUID;

public record TransferDto(UUID id, String clientId, String targetClientId, String sourceAccount, String targetAccount, String message,
                          int amount) {
}