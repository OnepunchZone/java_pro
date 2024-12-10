package services;

import model.Product;
import repo.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final ProductRepository productRepository;
    private final List<Product> cartItems = new ArrayList<>();

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProductById(int id) {
        productRepository.getProductById(id).ifPresent(cartItems::add);
    }

    public void removeProductById(int id) {
        cartItems.removeIf(product -> product.getId() == id);
    }

    public List<Product> getCartItems() {
        return new ArrayList<>(cartItems);
    }
}
