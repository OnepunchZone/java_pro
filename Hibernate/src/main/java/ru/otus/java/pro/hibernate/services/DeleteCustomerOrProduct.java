package ru.otus.java.pro.hibernate.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.otus.java.pro.hibernate.entity.Customer;
import ru.otus.java.pro.hibernate.entity.Product;

import java.util.Scanner;

public class DeleteCustomerOrProduct {
    public static void deleteCustomer(SessionFactory factory, Scanner sc) {
        try (Session session = factory.getCurrentSession()) {

            session.beginTransaction();
            System.out.println("Введите id покупателя, которого хотите удалить:");
            Long customerId = sc.nextLong();
            Customer customer = session.get(Customer.class, customerId);
            if(customer != null) {
                customer.getProducts().clear();
                session.flush();
                session.remove(customer);
                System.out.println("Покупатель " + customer.getName() + " удален из списка.");
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(SessionFactory factory, Scanner sc) {
        try (Session session = factory.getCurrentSession()) {

            session.beginTransaction();
            System.out.println("Введите id товара, который хотите удалить:");
            Long productId = sc.nextLong();
            Product product = session.get(Product.class, productId);
            if(product != null) {
                product.getCustomers().clear();
                session.flush();
                session.remove(product);
                System.out.println("Продукт " + product.getTitle() + " удален из списка.");
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
