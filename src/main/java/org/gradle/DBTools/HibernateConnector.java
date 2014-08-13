package org.gradle.DBTools;

import java.io.Serializable;
import java.util.List;

import org.gradle.business.Contact;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnector implements Serializable {
	private static SessionFactory sf;
	  private static final HibernateConnector instance;
	  static {
	        try {
	            instance = new HibernateConnector();
	        } catch (Exception e) {
	            throw new RuntimeException("error", e);
	        }
	    }

	private HibernateConnector() {
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

	/**
	 * inserts a single contact
	 * 
	 * @param c
	 *            a contact to insert
	 */
	public void insertContact(Contact c) {
		Session s = sf.openSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
		s.close();

	}

	/**
	 * insert list of contacts
	 * 
	 * @param contacts
	 *            list of contacts to be inserted
	 */
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
	 public static HibernateConnector getInstance() {
	        return instance;
	    }
	 

}
