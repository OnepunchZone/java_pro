package ru.otus.java.pro.hibernate.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ForcePrepareDataServ {
    public static void start(SessionFactory factory) {

        try (Session session = factory.getCurrentSession()) {

            String sql = Files.lines(Paths.get("Hibernate/full.sql")).collect(Collectors.joining(" "));
            session.beginTransaction();
            session.createNativeMutationQuery(sql).executeUpdate();
            session.getTransaction().commit();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
