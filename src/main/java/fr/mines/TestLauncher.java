package fr.mines;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.persistence.JPAUtils;

public class TestLauncher {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestLauncher.class);

	public static void main(String[] args) {
		EntityManager entityManager = JPAUtils.createEntityManager();
		Query query = entityManager.createNativeQuery("SELECT 1");
		query.getFirstResult();
		entityManager.close();
		JPAUtils.getEntityManagerFactory().close();
		LOGGER.info("Test ok, connected to database");
	}

}
