package ru.otus.java.pro.spring.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.java.pro.spring.app.entities.Item;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long> {
}
