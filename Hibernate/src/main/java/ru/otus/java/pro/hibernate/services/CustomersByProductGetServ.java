package ru.otus.java.pro.hibernate.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.otus.java.pro.hibernate.entity.Product;

import java.util.Scanner;

public class CustomersByProductGetServ {
    public static void start(SessionFactory factory, Scanner sc) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            System.out.println("Введите id продукта :");
            Long productId = sc.nextLong();
            Product product = session.get(Product.class, productId);

            if (product != null) {
                System.out.println("Товар " + product.getTitle() + " купили:");
                product.getCustomers().forEach(customer -> System.out.println(customer.getName()));
            } else {
                System.out.println("Товара с таким id не существет.");
            }

            session.getTransaction().commit();
        }
    }
}
