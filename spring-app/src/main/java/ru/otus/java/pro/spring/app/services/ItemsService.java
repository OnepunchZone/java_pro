package ru.otus.java.pro.spring.app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.java.pro.spring.app.dtos.ItemDto;
import ru.otus.java.pro.spring.app.entities.Item;
import ru.otus.java.pro.spring.app.repositories.ItemsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemsService {
    private final ItemsRepository itemsRepository;

    public Optional<Item> getItemById(Long id) {
        return itemsRepository.findById(id);
    }

    public Item createNewItem(ItemDto itemDto) {
        Item item = new Item(null, itemDto.title(), itemDto.price());
        return itemsRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemsRepository.findAll();
    }
}
