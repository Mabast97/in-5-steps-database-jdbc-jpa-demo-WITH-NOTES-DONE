package in5stepsdatabast;

import in5stepsdatabast.databasedemo.PersonJdbcDAO;
import in5stepsdatabast.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication   // because of the JPA class, we make it as comment
public class SpringJdbcDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonJdbcDAO dao;

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
		System.out.println("Done/");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users == {}",dao.findAll());
		logger.info("User Id 10002 == {}",dao.findById(10002));
		logger.info("Deleting Id 10003, Number of rows Deleted is  == {}",dao.deleteById(10003));
		logger.info("NEW All users == {}",dao.findAll());
		logger.info("Inserting 10004 == {}",dao.insert(new Person(10004, "Sara", "Berlin", new Date())));
		logger.info("Inserting 10004 == {}",dao.update(new Person(10002, "Karim", "Poland", new Date())));
		logger.info("NEW All users == {}",dao.findAll());

	}
}
