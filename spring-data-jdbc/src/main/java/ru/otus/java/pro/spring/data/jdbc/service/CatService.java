package ru.otus.java.pro.spring.data.jdbc.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.java.pro.spring.data.jdbc.entity.Cat;
import ru.otus.java.pro.spring.data.jdbc.repository.CatsRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CatService {
    private final CatsRepo catsRepo;

    public CatService(CatsRepo catsRepo) {
        this.catsRepo = catsRepo;
    }

    @Transactional
    public void addCat(String name, String color) {
        Cat newCat = new Cat(null, name, color);
        catsRepo.save(newCat);
    }

    @Transactional
    public List<Cat> findAllCats() {
        return catsRepo.findAll();
    }

    @Transactional
    public Optional<Cat> findCatById(Long id) {
        return catsRepo.findById(id);
    }

    @Transactional
    public void deleteCat(Long id) {
        catsRepo.deleteById(id);
    }

    @Transactional
    public void updateCat(Long id, String name, String color) {
        catsRepo.save(new Cat(id, name, color));
    }
}
