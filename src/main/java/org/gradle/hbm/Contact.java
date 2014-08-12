package org.gradle.hbm;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACT")
public class Contact implements Comparable<Contact>, Comparator<Contact> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DOB")
	private String dob;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PHONE")
	private String phone;

	public Contact() {
	}

	public Contact(String name, String dob, String email, String phone) {
		super();
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
	}

	public Long getId() {
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

	// @Override
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

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", dob=" + dob
				+ ", email=" + email + ", phone=" + phone + "]";
	}

}
