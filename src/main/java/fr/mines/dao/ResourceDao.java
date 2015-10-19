package fr.mines.dao;

import java.util.List;

import javax.persistence.Query;

import fr.mines.entitites.Resource;

/**
 * DAO for resource
 */
public class ResourceDao extends AbstractDao<Resource, Long> {
	private static ResourceDao instance;

	private ResourceDao() {
		super(Resource.class);
	}

	public static ResourceDao getInstance() {
		if (instance == null) {
			instance = new ResourceDao();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getAll() {
		Query selectQuery = this.getEntityManager().createQuery("SELECT r FROM Resource r");
		return selectQuery.getResultList();
	}

}
