package fr.mines.entitites;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="MAIL")
	private String mail;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="LOGIN")
	private String login;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ADMIN")
	private boolean admin;
	
	@OneToMany(mappedBy="user")
	private Collection<Reservation> reservations;
	
	public User(){
	}

	public User(int id, String firstName, String lastName, String mail, String phone, String login, String password,
			boolean admin, Collection<Reservation> reservations) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.phone = phone;
		this.login = login;
		this.password = password;
		this.admin = admin;
		this.reservations = reservations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Collection<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}
	
}
