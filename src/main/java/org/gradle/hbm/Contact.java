package org.gradle.hbm;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact implements Comparable<Contact>,Comparator<Contact> {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "dob")
	private String dob;
	@Column(name = "email")
	private String email;
	@Column(name = "phone")
	private String phone;

	/**
	 * @param id
	 * @param name
	 * @param dob
	 * @param email
	 * @param phone
	 */
	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public Contact(String name, String dob, String email, String phone) {
		super();

		this.name = name;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Contact o) {
		if (this.email.equals(o.getEmail()))
			return 0;
		else
			return 1;
	}

	@Override
	public int compare(Contact o1, Contact o2) {
		// TODO Auto-generated method stub
		
		return o1.compareTo(o2);
	}
}
