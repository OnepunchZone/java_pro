package ru.otus.java.pro.spring.app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.java.pro.spring.app.entities.Account;
import ru.otus.java.pro.spring.app.exceptions_handling.BusinessLogicException;
import ru.otus.java.pro.spring.app.repositories.AccountRepo;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final AccountRepo accountRepo;

    public void transfer(String senderAccountNumber, String receiverAccountNumber, int amount) {
        if (amount <= 0) {
            throw new BusinessLogicException("Счёт отправителя должен быть больше нуля.");
        }

        Account sender = accountRepo.findByAccountNumber(senderAccountNumber)
                .orElseThrow(() -> new BusinessLogicException("Счёт отправителя не найден."));
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

        /*Transfer transfer = new Transfer(sender.getId(), receiver.getId(), amount);
        transfersRepo.save(transfer);*/
    }
}
