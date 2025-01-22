import config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import repo.ProductRepository;
import services.Cart;

import java.util.Scanner;

@ComponentScan
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Scanner in = new Scanner(System.in);
        ProductRepository productRepository = context.getBean(ProductRepository.class);

        while (true) {
            System.out.println("Выберите вариант от 1 до 3, где: ");
            System.out.println("1 - Показать все товары");
            System.out.println("2 - Создать корзину");
            System.out.println("3 - Выход");
            int input = in.nextInt();

            if (input == 1) {
                productRepository.getAllProducts().forEach(System.out::println);
            } else if (input == 2) {
                Cart cart = context.getBean(Cart.class);
                manageCart(in, cart);
            } else if (input == 3) {
                System.out.println("Выход из программы.");
                return;
            } else {
                System.out.println("Нет такого варианата. Выберите вариант от 1 до 3.");
            }
        }
    }

    private static void manageCart(Scanner scanner, Cart cart) {
        while (true) {
            System.out.println("Выберите вариант от 1 до 4, где: ");
            System.out.println("1 - Добавить товар в корзину");
            System.out.println("2 - Удалить товар из корзины");
            System.out.println("3 - Показать содержимое корзины");
            System.out.println("4 - Выйти из корзины");
            int input = scanner.nextInt();


            if (input == 1) {
                System.out.print("Введите ID товара: ");
                int id = scanner.nextInt();
                cart.addProductById(id);
            } else if (input == 2) {
                System.out.print("Введите ID товара для удаления: ");
                int id = scanner.nextInt();
                cart.removeProductById(id);
            } else if (input == 3) {
                cart.getCartItems().forEach(System.out::println);
            } else if (input == 4) {
                System.out.println("Выход из корзины.");
                return;
            } else {
                System.out.println("Нет такого варианата. Выберите вариант от 1 до 4.");
            }

        }
    }
}
