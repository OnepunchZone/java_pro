package ru.otus.java.pro.spring.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.java.pro.spring.app.dtos.ProductDto;
import ru.otus.java.pro.spring.app.entities.Product;
import ru.otus.java.pro.spring.app.repositories.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public List<ProductDto> getAllProducts() {
        return productRepo.getAllProducts()
                .stream()
                .map(product -> new ProductDto(product.getId(), product.getTitle(), product.getPrice()))
                .toList();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepo.findProductById(id);
    }

    public Product createProduct(ProductDto productDto) {
        Product newProduct = new Product(null, productDto.getTitle(), productDto.getPrice());
        productRepo.addNewProduct(newProduct);
        return newProduct;
    }
}

