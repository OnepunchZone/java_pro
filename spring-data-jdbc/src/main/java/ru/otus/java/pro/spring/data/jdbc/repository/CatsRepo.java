package ru.otus.java.pro.spring.data.jdbc.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.java.pro.spring.data.jdbc.entity.Cat;

@Repository
public interface CatsRepo extends ListCrudRepository<Cat, Long> {
}
