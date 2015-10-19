package fr.mines.entitites;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESOURCE")
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "LOCALISATION")
	private String localisation;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User manager;

	//TODO : delete
	@OneToOne
	@JoinColumn(name = "RESOURCE_TYPE_ID")
	private ResourceType resourceType;

	@OneToMany(mappedBy = "resource")
	private Collection<Reservation> reservations;

	public Resource() {
		//JPA
	}

	public Resource(String name, String description, String localisation, User manager, ResourceType resourceType) {
		super();
		this.name = name;
		this.description = description;
		this.localisation = localisation;
		this.manager = manager;
		this.resourceType = resourceType;
	}

	public void copyIn(Resource previousUser) {
		previousUser.setName(name);
		previousUser.setDescription(description);
		previousUser.setLocalisation(localisation);
		previousUser.setResourceType(resourceType);
		previousUser.setManager(manager);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Collection<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}
}