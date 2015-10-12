package fr.mines.entitites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RESERVATION")
public class Reservation {
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="RESERVATION_START")
	private Date reservationStart;
	
	@Column(name="RESERVATION_STOP")
	private Date reservationStop;
	
	@OneToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	@OneToOne
	@JoinColumn(name="RESOURCE_ID")
	private Resource resource;

	public Reservation() {
	}
	
	public Reservation(int id, Date reservationStart, Date reservationStop, User user, Resource resource) {
		this.id = id;
		this.reservationStart = reservationStart;
		this.reservationStop = reservationStop;
		this.user = user;
		this.resource = resource;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReservationStart() {
		return reservationStart;
	}

	public void setReservationStart(Date reservationStart) {
		this.reservationStart = reservationStart;
	}

	public Date getReservationStop() {
		return reservationStop;
	}

	public void setReservationStop(Date reservationStop) {
		this.reservationStop = reservationStop;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	
}
