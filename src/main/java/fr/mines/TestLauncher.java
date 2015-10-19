package fr.mines;

import fr.mines.entitites.User;
import fr.mines.service.ServiceExecutionException;
import fr.mines.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLauncher {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestLauncher.class);

	public static void main(String[] args) throws ServiceExecutionException {
		User create = UserService.getInstance().create(new User("Mathieu", "THEBAUD", "mail@test.com", "123456", "math", "pass", false));
		LOGGER.info("Added");
		LOGGER.info("Test ok, connected to database");
	}

}
