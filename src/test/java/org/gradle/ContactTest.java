package org.gradle;

import static org.junit.Assert.*;

import java.util.List;

import org.gradle.hbm.Contact;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactTest {
	private static SessionFactory sf;

	@BeforeClass
	public static void setUp() throws Exception {
		sf = new Configuration().configure("hibernate-test.cfg.xml")
				.buildSessionFactory();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		sf.close();
	}

	@Test
	public void test1Save() {
		System.out.println("testing save");
		Contact c = new Contact();
		c.setName("dummy name");
		c.setDob("blah");
		c.setEmail("whatever@blah.sh");
		c.setPhone("0123456");
		Session s = sf.openSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
		s.close();
	}

	@Test
	public void test2List() {
		Session s = sf.openSession();
		s.beginTransaction();
		Query q = s.createQuery("select c from Contact c");
		List<Contact> contacts = q.list();
		System.out.println("created contacts are:");
		System.out.println(contacts);
		s.getTransaction().commit();
		s.close();
	}
}
