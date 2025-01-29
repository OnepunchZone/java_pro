package ru.otus.java.pro.hibernate.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.otus.java.pro.hibernate.entity.Customer;

import java.util.Scanner;

public class ProductsByCustomerGetServ {
    public static void start(SessionFactory factory, Scanner sc) {

        try (Session session = factory.getCurrentSession()) {

            session.beginTransaction();
            System.out.println("Введите id покупателя:");
            Long customerId = sc.nextLong();
            Customer customer = session.get(Customer.class, customerId);

            if (customer != null) {
                System.out.println("Товары покупателя " + customer.getName() + ":");
                customer.getProducts().forEach(product ->
                        System.out.println(product.getTitle() + " - " + product.getPrice()));
            } else {
                System.out.println("Покупатель с таким id не найден.");
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
