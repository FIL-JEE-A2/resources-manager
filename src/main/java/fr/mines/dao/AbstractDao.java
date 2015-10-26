package fr.mines.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.mines.entitites.MergeableEntity;
import fr.mines.persistence.JPAUtils;

public abstract class AbstractDao<T extends MergeableEntity<T>, K> {
	private Class<T> type;

	protected AbstractDao(Class<T> typeP) {
		this.type = typeP;
	}

	protected EntityManager entityManager()
	{
		return JPAUtils.getEntityManager();
	}

	public T create(T item) {
		EntityTransaction transaction = entityManager().getTransaction();
		transaction.begin();
		entityManager().persist(item);
		transaction.commit();
		return item;
	}

	public T update(K id, T item) {
		T previousItem = entityManager().find(type, id);
		EntityTransaction transaction = entityManager().getTransaction();
		transaction.begin();
		item.copyIn(previousItem);
		transaction.commit();
		return item;
	}

	public T remove(K id) {
		T previousItem = entityManager().find(type, id);
		EntityTransaction transaction = entityManager().getTransaction();
		transaction.begin();
		entityManager().remove(previousItem);
		transaction.commit();
		return previousItem;
	}

	public T get(K id) {
		return entityManager().find(type, id);
	}

	/* TODO : Supprimer si définitivement inutile
	protected T refresh(T entity) {
		if (entity != null) entityManager().refresh(entity);
		return entity;
	}

	protected List<T> refreshAll(List<T> list) {
		for (T t : list) refresh(t);
		return list;
	}
	*/

	protected abstract List<T> getAllImpl();

	public List<T> getAll() {
		return getAllImpl();
	}

}
