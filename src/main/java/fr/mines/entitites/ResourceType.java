package fr.mines.entitites;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="RESOURCE_TYPE")
public class ResourceType {
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="TYPE")
	private String type;
	
	@OneToMany
	@JoinColumn(name="RESOURCE_TYPE_ID")
	private Collection<Resource> resources;
	
	public ResourceType() {
	}

	public ResourceType(int id, String type, Collection<Resource> resources) {
		super();
		this.id = id;
		this.type = type;
		this.resources = resources;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Collection<Resource> getResources() {
		return resources;
	}

	public void setResources(Collection<Resource> resources) {
		this.resources = resources;
	}
	
}
