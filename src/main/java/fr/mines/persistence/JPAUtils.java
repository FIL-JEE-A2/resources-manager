package fr.mines.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Useful class to provide entity manager from persistence unit.
 */
public class JPAUtils {
	public static final ThreadLocal<EntityManager> ENTITY_MANAGERS = new ThreadLocal<EntityManager>();

	public static EntityManager getEntityManager() {
		return ENTITY_MANAGERS.get();
	}
}
