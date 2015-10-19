package fr.mines.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.mines.entitites.Resource;
import fr.mines.persistence.JPAUtils;

/**
 * DAO for resource
 */
public class ResourceDao {
	private static ResourceDao instance;
	private EntityManager entityManager;

	private ResourceDao() {
		entityManager = JPAUtils.createEntityManager();
	}

	public static ResourceDao getInstance() {
		if (instance == null) {
			instance = new ResourceDao();
		}
		return instance;
	}

	public Resource create(Resource resource) {
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.persist(resource);
		transaction.commit();
		return resource;

	}

	public Resource update(Long id, Resource resource) {
		Resource previousResource = this.entityManager.find(Resource.class, id);
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		resource.copyIn(previousResource);
		transaction.commit();
		return resource;
	}

	public Resource get(Long id) {
		return this.entityManager.find(Resource.class, id);
	}

	public Resource remove(Long id) {
		Resource previousResource = this.entityManager.find(Resource.class, id);
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.remove(previousResource);
		transaction.commit();
		return previousResource;
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getAll() {
		Query selectQuery = this.entityManager.createQuery("SELECT r FROM Resource r");
		return selectQuery.getResultList();
	}

}
