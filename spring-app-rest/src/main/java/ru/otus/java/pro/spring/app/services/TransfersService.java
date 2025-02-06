package ru.otus.java.pro.spring.app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.java.pro.spring.app.dtos.ExecuteTransferDtoRq;
import ru.otus.java.pro.spring.app.entities.Account;
import ru.otus.java.pro.spring.app.entities.Transfer;
import ru.otus.java.pro.spring.app.exceptions_handling.BusinessLogicException;
import ru.otus.java.pro.spring.app.exceptions_handling.ValidationException;
import ru.otus.java.pro.spring.app.exceptions_handling.ValidationFieldError;
import ru.otus.java.pro.spring.app.repositories.AccountRepo;
import ru.otus.java.pro.spring.app.repositories.TransfersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransfersService {
    private final TransfersRepository transfersRepository;
    private final AccountRepo accountRepo;

    public Optional<Transfer> getTransferById(UUID id, String clientId) {
        return transfersRepository.findByIdAndClientId(id, clientId);
    }

    public List<Transfer> getAllTransfers(String clientId) {
        return transfersRepository.findByClientIdOrTargetClientId(clientId, clientId);
    }

    @Transactional
    public void execute(String clientId, ExecuteTransferDtoRq request) {
        validateExecuteTransferDtoRq(request);

        transferStart(clientId, request.sourceAccount(), request.targetAccount(), request.amount());

        Transfer transfer = new Transfer(null, clientId, request.targetClientId(),
                request.sourceAccount(), request.targetAccount(),
                request.message(), request.amount());
        transfersRepository.save(transfer);
    }

    private void transferStart(String clientId, String senderAccountNumber, String receiverAccountNumber, int amount) {
        if (amount <= 0) {
            throw new BusinessLogicException("Счёт отправителя должен быть больше нуля.");
        }

        Account sender = accountRepo.findByAccountNumber(senderAccountNumber)
                .orElseThrow(() -> new BusinessLogicException("Счёт отправителя не найден."));

        if (!sender.getCustomerId().equals(clientId)) {
            throw new BusinessLogicException("Клиент не может переводить деньги с чужого счёта.");
        }

        Account receiver = accountRepo.findByAccountNumber(receiverAccountNumber)
                .orElseThrow(() -> new BusinessLogicException("Счёт получателя не найден"));

        if (sender.isBlocked() || receiver.isBlocked()) {
            throw new BusinessLogicException("Один из счетов заблокирован");
        }

        if (sender.getBalance() < 0) {
            throw new BusinessLogicException("Недостаточно средств.");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        accountRepo.save(sender);
        accountRepo.save(receiver);
    }

    private void validateExecuteTransferDtoRq(ExecuteTransferDtoRq executeTransferDtoRq) {
        List<ValidationFieldError> errors = new ArrayList<>();
        if (executeTransferDtoRq.sourceAccount().length() != 12) {
            errors.add(new ValidationFieldError("sourceAccount", "Длина поля счет отправителя должна составлять 12 символов"));
        }
        if (executeTransferDtoRq.targetAccount().length() != 12) {
            errors.add(new ValidationFieldError("targetAccount", "Длина поля счет получателя должна составлять 12 символов"));
        }
        if (executeTransferDtoRq.amount() <= 0) {
            errors.add(new ValidationFieldError("amount", "Сумма перевода должна быть больше 0"));
        }
        if (!errors.isEmpty()) {
            throw new ValidationException("EXECUTE_TRANSFER_VALIDATION_ERROR", "Проблемы заполнения полей перевода", errors);
        }
    }
}
