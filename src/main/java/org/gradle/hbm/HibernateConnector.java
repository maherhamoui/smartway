package org.gradle.hbm;

import java.util.List;

import org.gradle.business.Contact;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnector {
	private static SessionFactory sf;

	public HibernateConnector() {
		// TODO Auto-generated constructor stub
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			sf = new Configuration().configure("hibernate.cfg.xml")
					.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}

	}

	public void insertContact(Contact c) {
		Session s = sf.openSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
		s.close();

	}

	public void insertContacts(List<Contact> contacts) {
		for (Contact c : contacts) {
			this.insertContact(c);
		}

	}

	public List<Contact> listContacts() {
		Session s = sf.openSession();
		s.beginTransaction();
		Query q = s.createQuery("select c from Contact c");
		List<Contact> contacts = q.list();
		s.getTransaction().commit();
		s.close();
		return contacts;
	}

}
