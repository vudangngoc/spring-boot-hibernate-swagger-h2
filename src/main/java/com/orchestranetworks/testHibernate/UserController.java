package com.orchestranetworks.testHibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@GetMapping("users")
	public List<User> getAllUser(){
		
		Session session = HibernateFactoryUtil.getInstance().openSession();
		Transaction tx = session.beginTransaction();
		List<User> result = session.createQuery("FROM User").list();
		tx.commit();
		return result;
	}
}
