package ru.otus.java.pro.spring.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.java.pro.spring.app.entities.Transfer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransfersRepository extends JpaRepository<Transfer, UUID> {
    Optional<Transfer> findByIdAndClientId(UUID id, String clientId);
    List<Transfer> findAllByClientId(String clientId);
    List<Transfer> findByClientIdOrTargetClientId(String clientId, String targetClientId);
}
