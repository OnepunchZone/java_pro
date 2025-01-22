package repo;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAllProducts();
    Optional<Product> getProductById(int id);
}
