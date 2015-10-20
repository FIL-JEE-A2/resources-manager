package fr.mines.service;

import java.util.List;

import fr.mines.dao.ResourceDao;
import fr.mines.entitites.Resource;
import fr.mines.entitites.ResourceType;
import fr.mines.entitites.User;

public class ResourceService extends AbstractService<Resource, Long, ResourceDao>
{
	private static ResourceService instance;

	private ResourceService() {
		super(ResourceDao.getInstance());
	}

	public static ResourceService getInstance() {
		return instance == null ? instance = new ResourceService() : instance;
	}
}