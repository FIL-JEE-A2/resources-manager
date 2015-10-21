package fr.mines;

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

public class TestLauncher {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestLauncher.class);

	public static void main(String[] args) throws ServiceExecutionException {
		try {
			Reservation resa = new Reservation(new Date(2015-1900, 9, 5), new Date(2015-1900, 9, 10));
			resa.setUser(UserService.getInstance().get(8L));
			resa.setResource(ResourceService.getInstance().get(1L));
			ReservationService.getInstance().create(resa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Added");
		LOGGER.info("Test ok, connected to database");
	}

}
