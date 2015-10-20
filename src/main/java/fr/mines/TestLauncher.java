package fr.mines;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.entitites.Reservation;
import fr.mines.service.ReservationService;
import fr.mines.service.ServiceExecutionException;

public class TestLauncher {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestLauncher.class);

	public static void main(String[] args) throws ServiceExecutionException {
		try {
			ReservationService.getInstance().create(new Reservation(new Date(2015-1900, 9, 5), new Date(2015-1900, 9, 10)), 8L, 1L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Added");
		LOGGER.info("Test ok, connected to database");
	}

}
