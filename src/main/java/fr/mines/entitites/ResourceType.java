package fr.mines.entitites;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RESOURCE_TYPE")
public class ResourceType implements MergeableEntity<ResourceType> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TYPE", unique = true)
	private String type;

	@OneToMany
	@JoinColumn(name = "RESOURCE_TYPE_ID")
	private Collection<Resource> resources;

	public ResourceType() {}

	public ResourceType(String type, Collection<Resource> resources) {
		super();
		this.type = type;
		this.resources = resources;
	}

	@Override
	public String toString()
	{
		return "ResourceType{" +
				"id=" + id +
				", type='" + type + '\'' +
				'}';
	}

	public ResourceType(String type) {
		this(type, new ArrayList<Resource>());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public void copyIn(ResourceType resourceType) {
		resourceType.setType(type);
		resourceType.setResources(resources);
	}
}
