package com.orchestranetworks.testHibernate;

import java.sql.SQLException;
import javax.annotation.PostConstruct;

import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

	}

	@PostConstruct
	private void initDb() {
		System.out.println(String.format("Creating and Inserting test data"));

		Session session = HibernateFactoryUtil.getInstance().openSession();
		Transaction tx = session.beginTransaction();
		session.createSQLQuery("drop table employees if exists").executeUpdate();
		session.createSQLQuery("create table employees(id serial,firstname varchar(255),lastname varchar(255))").executeUpdate();
		session.save(new User("Brat","Pitt"));
		session.save(new User("Will","Smith"));
		tx.commit();

	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseServer() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
	}
}
