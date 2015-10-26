package fr.mines;

import fr.mines.dao.ResourceDao;
import fr.mines.entitites.Reservation;
import fr.mines.entitites.Resource;
import fr.mines.entitites.User;
import fr.mines.service.ReservationService;
import fr.mines.service.ResourceService;
import fr.mines.service.ServiceExecutionException;
import fr.mines.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class TestLauncher {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestLauncher.class);

	public static void main(String[] args) throws ServiceExecutionException {
		try {
			List<Resource> freeResourceByDateAndResourceType = ResourceDao.getInstance().getFreeResourceByDateAndResourceType(2L,
					new Date(2015 - 1900, 9, 10), new Date(2015 - 1900, 9, 10));
			for (Resource resource : freeResourceByDateAndResourceType) {
				System.out.println(resource);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Added");
		LOGGER.info("Test ok, connected to database");
	}

}
