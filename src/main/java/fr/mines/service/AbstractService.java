package fr.mines.service;

import fr.mines.dao.AbstractDao;
import fr.mines.entitites.MergeableEntity;

import java.util.List;

/**
 * Created by valentin on 20/10/15.
 */
public class AbstractService<T extends MergeableEntity<T>, K, DAO extends AbstractDao<T,K>>
{
    protected DAO dao;

    AbstractService(DAO dao)
    {
        this.dao = dao;
    }

    protected void checkCreate(T toAdd) throws ServiceExecutionException{}

    protected void checkUpdate(T toUpdate, K idToUpdate) throws ServiceExecutionException{}

    protected void checkRemove(K idToRemove) throws ServiceExecutionException{}

    public T create(T toCreate) throws ServiceExecutionException
    {
        checkCreate(toCreate);
        return dao.create(toCreate);
    }

    public T update(K id, T toUpdate) throws ServiceExecutionException
    {
        checkUpdate(toUpdate, id);
        return dao.update(id, toUpdate);
    }

    public T remove(K id) throws ServiceExecutionException
    {
        checkRemove(id);
        return dao.remove(id);
    }

    public T get(K id)
    {
        return dao.get(id);
    }

    public List<T> getAll()
    {
        return dao.getAll();
    }
}
