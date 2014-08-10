package org.gradle.hbm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact {
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
	public Contact(long id, String name, String dob, String email, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
}
