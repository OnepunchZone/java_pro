package repo;

import model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> productLst = new ArrayList<>();

    public ProductRepositoryImpl() {

        if (productLst.isEmpty()) {
            productLst.add(new Product(1, "Пакет", 10.0));
            productLst.add(new Product(2, "Жвачка", 20.0));
            productLst.add(new Product(3, "Куринное филе", 388.0));
            productLst.add(new Product(4, "Торт", 477.0));
            productLst.add(new Product(5, "Новогодний набор конфет", 868.0));
            productLst.add(new Product(6, "Печенье", 99.0));
            productLst.add(new Product(7, "Готовый шашлык", 777.0));
            productLst.add(new Product(8, "Морковь", 54.0));
            productLst.add(new Product(9, "Гель для душа", 300.0));
            productLst.add(new Product(10, "Гирлянда", 425.0));
        }

    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productLst);
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productLst.stream().filter(p -> p.getId() == id).findFirst();
    }
}
