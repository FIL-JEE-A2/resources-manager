package fr.mines.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;

import fr.mines.entitites.MergeableEntity;
import fr.mines.persistence.JPAUtils;

public abstract class AbstractDao<T extends MergeableEntity<T>, K> {
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
			entityManager.setFlushMode(FlushModeType.COMMIT);
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
		T found = this.getEntityManager().find(type, id);
		return refresh(found);
	}

	protected T refresh(T entity) {
		if (entity != null) {
			this.getEntityManager().refresh(entity);
			return entity;
		} else {
			return null;
		}
	}

	protected List<T> refreshAll(List<T> list) {
		for (T t : list) {
			refresh(t);
		}
		return list;
	}

	//	protected void closeEntityManager() {
	//		EntityManager em = this.entityManagerThreadLocal.get();
	//		if (em != null) {
	//			em.close();
	//			this.entityManagerThreadLocal.set(null);
	//		}
	//	}

	protected abstract List<T> getAllImpl();

	public List<T> getAll() {
		return refreshAll(getAllImpl());
	}

}
