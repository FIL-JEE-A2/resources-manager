package fr.mines.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import fr.mines.entitites.ResourceType;

public class ResourceTypeDao extends AbstractDao<ResourceType, Long> {

	private ResourceTypeDao() {
		super(ResourceType.class);
	}

	private static ResourceTypeDao instance;

	public static ResourceTypeDao getInstance() {
		return instance == null ? instance = new ResourceTypeDao() : instance;
	}

	public ResourceType getByTypeName(String typeName) {
		Query query = entityManager().createQuery("SELECT rt FROM ResourceType rt WHERE rt.type=:typeName");
		query.setParameter("typeName", typeName);
		try {
			return (ResourceType) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ResourceType> getAllImpl() {
		Query selectQuery = entityManager().createQuery("SELECT rt FROM ResourceType rt");
		return selectQuery.getResultList();
	}
}
