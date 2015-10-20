package fr.mines.entitites;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESERVATION")
public class Reservation implements MergeableEntity<Reservation> {
	private final static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "RESERVATION_START")
	private Date reservationStart;

	@Column(name = "RESERVATION_STOP")
	private Date reservationStop;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToOne
	@JoinColumn(name = "RESOURCE_ID")
	private Resource resource;

	public Reservation() {}

	public Reservation(Date reservationStart, Date reservationStop, User user, Resource resource) {
		this.reservationStart = reservationStart;
		this.reservationStop = reservationStop;
		this.user = user;
		this.resource = resource;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getReservationStartLabel() {
		return reservationStart != null ? DATE_FORMAT.format(reservationStart) : "";
	}

	public String getReservationStopLabel() {
		return reservationStop != null ? DATE_FORMAT.format(reservationStop) : "";
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public void copyIn(Reservation other) {
		other.setReservationStart(this.reservationStart);
		other.setReservationStop(this.reservationStop);
		other.setResource(this.resource);
		other.setUser(this.user);
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationStart=" + reservationStart + ", reservationStop=" + reservationStop + ", user=" + user
				+ ", resource=" + resource + "]";
	}
}
