package org.gradle.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.gradle.hbm.Contact;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private static SessionFactory factory;

	public HibernateUtil() {
		// TODO Auto-generated constructor stub
		initHib();
	}

	public void initHib() {
		try {
			factory = new AnnotationConfiguration().configure().

			addAnnotatedClass(Contact.class).buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object.");
			throw new ExceptionInInitializerError(ex);
		}
	}

	public List<Contact> listContacts() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Contact> contacts = new ArrayList<Contact>();
		System.out.println("1");
		try {
			System.out.println("0");
			tx = session.beginTransaction();
			System.out.println("1");
			List t = session.createQuery(" FROM contacts").list();
			System.out.println("2");
			for (Iterator iterator = t.iterator(); iterator.hasNext();) {
				Contact contact = (Contact) iterator.next();
				contacts.add(contact);
				System.out.print("First Name: " + contact.getName());

			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.err.println("transaction error");
		} finally {
			session.close();
		}
		return contacts;
	}
}
