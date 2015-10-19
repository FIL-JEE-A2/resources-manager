package fr.mines.service;

import java.util.List;

import fr.mines.dao.ResourceDao;
import fr.mines.entitites.Resource;
import fr.mines.entitites.ResourceType;
import fr.mines.entitites.User;

public class ResourceService {
	private static ResourceService instance;
	private ResourceDao resourceDao;

	private ResourceService() {
		resourceDao = ResourceDao.getInstance();
	}

	public static ResourceService getInstance() {
		if (instance == null) {
			instance = new ResourceService();
		}
		return instance;
	}

	public Resource create(Resource resource, Long managerID, Long resourceTypeId) throws ServiceExecutionException {
		User user = UserService.getInstance().get(managerID);
		resource.setManager(user);
		ResourceType resourceType = ResourceTypeService.getInstance().get(resourceTypeId);
		resource.setResourceType(resourceType);
		return this.resourceDao.create(resource);
	}

	public List<Resource> getAll() throws ServiceExecutionException {
		return this.resourceDao.getAll();
	}

	public Resource update(Long id, Resource toUpdate, Long managerId, Long resourceTypeId) throws ServiceExecutionException {
		User user = UserService.getInstance().get(managerId);
		toUpdate.setManager(user);
		ResourceType resourceType = ResourceTypeService.getInstance().get(resourceTypeId);
		toUpdate.setResourceType(resourceType);
		return this.resourceDao.update(id, toUpdate);
	}

	public Resource get(Long id) throws ServiceExecutionException {
		return this.resourceDao.get(id);
	}

	public Resource remove(Long id) throws ServiceExecutionException {
		return this.resourceDao.remove(id);
	}
}