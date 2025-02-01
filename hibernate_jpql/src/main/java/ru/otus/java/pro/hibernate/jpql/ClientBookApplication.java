package ru.otus.java.pro.hibernate.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.java.pro.hibernate.jpql.entities.Address;
import ru.otus.java.pro.hibernate.jpql.entities.Client;
import ru.otus.java.pro.hibernate.jpql.entities.Phone;
import ru.otus.java.pro.hibernate.jpql.util.LiquibaseRunner;

import java.util.List;

public class ClientBookApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientBookApplication.class);

    private ClientBookApplication() {
    }

    public static void main(String[] args) {

        try {
            LiquibaseRunner.runMigrations();
            LOGGER.info("Созданы и наполнены таблицы.");

            createAndGetClient();
        } catch (Exception e) {
            LOGGER.error("Ошибка при выполнении программы: ", e);
        }
    }

    private static void createAndGetClient() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Address address = new Address();
        address.setStreet("г. Буратиново, ул. Карабаса, д.5 кв.36");

        Client client = new Client();
        client.setName("Лёха");
        client.setAddress(address);
        LOGGER.info("Добавлен новый клиент " + client.getName());

        em.persist(address);
        em.persist(client);

        Phone phone = new Phone();
        phone.setNumber("111-00-1234-77");
        phone.setClient(client);
        LOGGER.info("Добавлен новый телефон " + phone.getNumber() + " клиента " + client.getName());
        em.persist(phone);

        client.getPhones().add(phone);

        em.getTransaction().commit();

        List<Client> clientLst = em
                .createQuery("SELECT C FROM Client C LEFT JOIN FETCH C.phones", Client.class)
                .getResultList();

        em.close();

        clientLst.forEach(c -> LOGGER.info("{}", c));

    }
}
