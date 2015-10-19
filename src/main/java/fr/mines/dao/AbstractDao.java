package fr.mines.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.mines.entitites.MergableEntity;
import fr.mines.persistence.JPAUtils;

public abstract class AbstractDao<T extends MergableEntity<T>, K> {
	private Class<T> type;
	private ThreadLocal<EntityManager> entityManagerThreadLocal;

	protected AbstractDao(Class<T> typeP) {
		this.type = typeP;
		this.entityManagerThreadLocal = new ThreadLocal<>();
	}

	protected EntityManager getEntityManager() {
		EntityManager entityManager = this.entityManagerThreadLocal.get();
		if (entityManager == null) {
			entityManager = JPAUtils.createEntityManager();
			entityManagerThreadLocal.set(entityManager);
		}
		return entityManager;
	}

	public T create(T item) {
		EntityTransaction transaction = this.getEntityManager().getTransaction();
		transaction.begin();
		this.getEntityManager().persist(item);
		transaction.commit();
		return item;
	}

	public T update(K id, T item) {
		T previousItem = this.getEntityManager().find(type, id);
		EntityTransaction transaction = this.getEntityManager().getTransaction();
		transaction.begin();
		item.copyIn(previousItem);
		transaction.commit();
		return item;
	}

	public T remove(K id) {
		T previousItem = this.getEntityManager().find(type, id);
		EntityTransaction transaction = this.getEntityManager().getTransaction();
		transaction.begin();
		this.getEntityManager().remove(previousItem);
		transaction.commit();
		return previousItem;
	}

	public T get(K id) {
		return this.getEntityManager().find(type, id);
	}

	public abstract List<T> getAll();

}
