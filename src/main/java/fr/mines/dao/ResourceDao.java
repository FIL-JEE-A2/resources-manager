package fr.mines.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		return instance == null ? instance = new ResourceDao() : instance;
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getAllImpl() {
		Query selectQuery = entityManager().createQuery("SELECT r FROM Resource r");
		return selectQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getResourceByManager(Long managerID) {
		Query selectQuery = entityManager().createQuery("SELECT r FROM Resource r JOIN r.manager m WHERE m.id=:managerID");
		selectQuery.setParameter("managerID", managerID);
		return selectQuery.getResultList();
	}

	Logger LOGGER = LoggerFactory.getLogger(ResourceDao.class);

	@SuppressWarnings("unchecked")
	public List<Resource> getFreeResourceByDateAndResourceType(Long resourceTypeId, Date startDate, Date stopDate) {
		Query selectQuery = entityManager().createQuery("SELECT DISTINCT r FROM Resource r "//
				+ " LEFT OUTER JOIN r.resourceType type LEFT OUTER JOIN r.reservations reserv WHERE "//
				+ "type.id = :resourceTypeID " //
				+ "AND (reserv IS NULL "//
				+ "OR reserv.reservationStart > :stopDate "//
				+ "OR reserv.reservationStop < :startDate )");
		LOGGER.info("Get resource of type {} from {} to {}", resourceTypeId, startDate, stopDate);
		selectQuery.setParameter("resourceTypeID", resourceTypeId);
		selectQuery.setParameter("stopDate", stopDate);
		selectQuery.setParameter("startDate", startDate);
		return selectQuery.getResultList();
	}
}