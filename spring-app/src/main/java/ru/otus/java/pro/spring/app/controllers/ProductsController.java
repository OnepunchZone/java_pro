package ru.otus.java.pro.spring.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.java.pro.spring.app.dtos.ProductDto;
import ru.otus.java.pro.spring.app.entities.Product;
import ru.otus.java.pro.spring.app.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/products")
public class ProductsController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getProductById(id).map(i -> new ProductDto(i.getId(), i.getTitle(), i.getPrice())).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product newProduct = productService.createProduct(productDto);
        return new ProductDto(newProduct.getId(), newProduct.getTitle(), newProduct.getPrice());
    }
}
