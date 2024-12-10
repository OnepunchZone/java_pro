package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repo.ProductRepository;
import repo.ProductRepositoryImpl;
import services.Cart;

@Configuration
public class AppConfig {
    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryImpl();
    }

    @Bean
    public Cart cart(ProductRepository productRepository) {
        return new Cart(productRepository);
    }
}
