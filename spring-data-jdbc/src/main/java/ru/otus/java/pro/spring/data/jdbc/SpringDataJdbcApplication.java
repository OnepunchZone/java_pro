package ru.otus.java.pro.spring.data.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.java.pro.spring.data.jdbc.repository.CatsRepo;
import ru.otus.java.pro.spring.data.jdbc.service.CatService;

@SpringBootApplication
public class SpringDataJdbcApplication implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(CatService.class);
	private final CatService catService;

    public SpringDataJdbcApplication(CatService catService) {
        this.catService = catService;
    }


    public static void main(String[] args) {
		SpringApplication.run(SpringDataJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Коты : {}", catService.findAllCats());
		catService.addCat("Вася", "черепаховый");
		LOGGER.info("Коты : {}", catService.findAllCats());
		LOGGER.info("Кот : {}", catService.findCatById(2L));
		catService.deleteCat(3L);
		LOGGER.info("Коты : {}", catService.findAllCats());
		catService.updateCat(1L, "Черномор", "чёрный");
		LOGGER.info("Коты : {}", catService.findAllCats());
	}
}
