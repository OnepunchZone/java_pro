package ru.otus.java.pro.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ru.otus.java.pro.hibernate.entity.Customer;
import ru.otus.java.pro.hibernate.entity.Product;
import ru.otus.java.pro.hibernate.services.CustomersByProductGetServ;
import ru.otus.java.pro.hibernate.services.DeleteCustomerOrProduct;
import ru.otus.java.pro.hibernate.services.ForcePrepareDataServ;
import ru.otus.java.pro.hibernate.services.ProductsByCustomerGetServ;

import java.util.Scanner;

public class CustomerPurchasesApplication {
    public static void main(String[] args) {
        startApp();
    }

    private static void startApp() {
        Scanner scanner = new Scanner(System.in);

        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory()) {

            ForcePrepareDataServ.start(factory);

            while (true) {
                System.out.println("Выберите число для дальнейших действий:");
                System.out.println("1. Посмотреть кто купил продукт по id продукта");
                System.out.println("2. Посмотреть список продуктов покупателя по его id");
                System.out.println("3. Удалить покупателя по id");
                System.out.println("4. Удалить продукт по id");
                System.out.println("5. Выход");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> CustomersByProductGetServ.start(factory, scanner);
                    case 2 -> ProductsByCustomerGetServ.start(factory, scanner);
                    case 3 -> DeleteCustomerOrProduct.deleteCustomer(factory, scanner);
                    case 4 -> DeleteCustomerOrProduct.deleteProduct(factory, scanner);
                    case 5 -> {
                        factory.close();
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Неверный выбор. Выберите число от 1 до 5.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
