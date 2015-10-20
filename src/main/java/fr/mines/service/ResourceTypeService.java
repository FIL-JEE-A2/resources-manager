package fr.mines.service;

import fr.mines.dao.ResourceTypeDao;
import fr.mines.entitites.Resource;
import fr.mines.entitites.ResourceType;

import java.util.List;

/**
 * Created by valentin on 19/10/15.
 */
public class ResourceTypeService {
    private static ResourceTypeService instance;
    private ResourceTypeDao resourceTypeDao;

    private ResourceTypeService() {
        resourceTypeDao = ResourceTypeDao.getInstance();
    }

    public static ResourceTypeService getInstance() {
        return instance == null ? instance = new ResourceTypeService() : instance;
    }

    public ResourceType create(ResourceType resourceType) throws ServiceExecutionException {
        checkResourceTypeConstraint(resourceType, null);
        return resourceTypeDao.create(resourceType);
    }

    public ResourceType get(Long id) throws ServiceExecutionException {
        return resourceTypeDao.get(id);
    }

    public ResourceType remove(Long id) throws ServiceExecutionException {
        checkResourceTypeRemoveConstraint(id);
        return resourceTypeDao.remove(id);
    }

    public ResourceType update(Long id, ResourceType toUpdate) throws ServiceExecutionException {
        checkResourceTypeConstraint(toUpdate, id);
        return resourceTypeDao.update(id, toUpdate);
    }

    public List<ResourceType> getAll() throws ServiceExecutionException {
        return resourceTypeDao.getAll();
    }

    private void checkResourceTypeConstraint(ResourceType resourceType, Long id) throws ServiceExecutionException {
        ResourceType byTypeName = resourceTypeDao.getByTypeName(resourceType.getType());
        if(byTypeName != null && (id == null || !id.equals(byTypeName.getId())))
            throw new ServiceExecutionException("Le type de ressource " + resourceType.getType() + " existe déjà !");
    }

    private void checkResourceTypeRemoveConstraint(Long id) throws ServiceExecutionException
    {
        ResourceType rt = get(id);
        if(rt == null)
            throw new ServiceExecutionException("Aucun type de resource ne corespond à l'identifiant "+id+".");
        if(rt.getResources().size() > 0)
            throw new ServiceExecutionException("Il existe des resource du type "+rt.getType()+". Impossible de le supprimer.");
    }
}
