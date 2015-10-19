package fr.mines.dao;

import fr.mines.entitites.Resource;
import fr.mines.entitites.ResourceType;
import fr.mines.persistence.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by valentin on 19/10/15.
 */
public class ResourceTypeDao {
    private EntityManager entityManager;

    private ResourceTypeDao() {
        entityManager = JPAUtils.createEntityManager();
    }

    private static ResourceTypeDao instance;

    public static ResourceTypeDao getInstance() {
        return instance == null ? instance = new ResourceTypeDao() : instance;
    }

    public ResourceType create(ResourceType resourceType) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(resourceType);
        transaction.commit();
        return resourceType;
    }

    public ResourceType update(Long id, ResourceType resourceType) {
        ResourceType previousResourceType = entityManager.find(ResourceType.class, id);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        resourceType.copyIn(previousResourceType);
        transaction.commit();
        return resourceType;
    }

    public ResourceType getByTypeName(String typeName)
    {
        Query query = entityManager.createQuery(
                "SELECT rt FROM ResourceType rt WHERE rt.type=:typeName");
        query.setParameter("typeName", typeName);
        try {
            return (ResourceType) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<ResourceType> getAll() {
        Query selectQuery = entityManager.createQuery("SELECT rt FROM ResourceType rt");
        return selectQuery.getResultList();
    }

    public ResourceType get(Long id) {
        return entityManager.find(ResourceType.class, id);
    }

    public ResourceType remove(Long id) {
        ResourceType previousResourceType = entityManager.find(ResourceType.class, id);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(previousResourceType);
        transaction.commit();
        return previousResourceType;
    }
}
