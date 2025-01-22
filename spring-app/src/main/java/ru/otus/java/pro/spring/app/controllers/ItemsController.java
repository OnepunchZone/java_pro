package ru.otus.java.pro.spring.app.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.otus.java.pro.spring.app.dtos.ItemDto;
import ru.otus.java.pro.spring.app.entities.Item;
import ru.otus.java.pro.spring.app.services.ItemsService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

// @Component, @Controller, @RestController, @Service, @Repository

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemsController {
    private final ItemsService itemsService;

    private static final Function<Item, ItemDto> ENTITY_TO_DTO = i -> new ItemDto(i.getId(), i.getTitle(), i.getPrice());

    @GetMapping
    public List<ItemDto> getAllItems() {
        return itemsService.getAllItems().stream().map(ENTITY_TO_DTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable Long id) {
        return itemsService.getItemById(id).map(ENTITY_TO_DTO).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createNewItem(@RequestBody ItemDto createItemDto) {
        Item newItem = itemsService.createNewItem(createItemDto);
        return new ItemDto(newItem.getId(), newItem.getTitle(), newItem.getPrice());
    }
}
