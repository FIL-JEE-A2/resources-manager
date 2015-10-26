package fr.mines.service;

import fr.mines.dao.ResourceTypeDao;
import fr.mines.entitites.ResourceType;

public class ResourceTypeService extends AbstractService<ResourceType, Long, ResourceTypeDao>
{
    private static ResourceTypeService instance;

    private ResourceTypeService()
    {
        super(ResourceTypeDao.getInstance());
    }

    public static ResourceTypeService getInstance() {
        return instance == null ? instance = new ResourceTypeService() : instance;
    }

    @Override
    protected void checkCreate(ResourceType toCreate) throws ServiceExecutionException {
        ResourceType byTypeName = dao.getByTypeName(toCreate.getType());
        if(byTypeName != null)
            throw new ServiceExecutionException("Le type de ressource " + toCreate.getType() + " existe déjà.");
    }

    @Override
    protected void checkUpdate(ResourceType toUpdate, Long id) throws ServiceExecutionException
    {
        ResourceType byTypeName = dao.getByTypeName(toUpdate.getType());
        if(byTypeName != null && !id.equals(byTypeName.getId()))
            throw new ServiceExecutionException("Le type de ressource " + toUpdate.getType()+ " existe déjà.");
    }

    @Override
    protected void checkRemove(Long idToRemove) throws ServiceExecutionException
    {
        ResourceType rt = get(idToRemove);
        if(rt == null)
            throw new ServiceExecutionException("Aucun type de resource ne correspond à l'identifiant "+idToRemove+".");
        if(rt.getResources().size() > 0)
            throw new ServiceExecutionException("Il existe des ressources du type "+rt.getType()+". Impossible de le supprimer.");
    }
}
