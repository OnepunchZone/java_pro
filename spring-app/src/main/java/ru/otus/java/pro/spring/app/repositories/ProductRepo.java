package ru.otus.java.pro.spring.app.repositories;

import org.springframework.stereotype.Component;
import ru.otus.java.pro.spring.app.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepo {
    private final List<Product> products = new ArrayList<>();
    private Long idCount = 1L;

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> findProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void addNewProduct(Product newProduct) {
        newProduct.setId(idCount++);
        products.add(newProduct);
    }
}
