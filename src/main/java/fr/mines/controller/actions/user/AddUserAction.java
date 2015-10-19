package fr.mines.controller.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.User;
import fr.mines.service.ServiceExecutionException;
import fr.mines.service.UserService;

public class AddUserAction implements FrontActionI {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddUserAction.class);

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("userLastName") != null) {
			LOGGER.info("Add a user \"{}\"", request.getParameter("userLastName"));
			//Add a user
			User user = new User(request.getParameter("userFirstName"), request.getParameter("userLastName"), request.getParameter("userEmail"),
					request.getParameter("userPhone"), request.getParameter("userLogin"), request.getParameter("userPassword"),
					Boolean.parseBoolean(request.getParameter("userIsAdmin")));
			request.setAttribute("previousUser", user);
			try {
				UserService.getInstance().create(user);
				request.setAttribute("userAdded", true);
				request.setAttribute("userAddedName", user.getFirstName() + " " + user.getLastName() + " (" + user.getLogin() + ")");
			} catch (ServiceExecutionException e) {
				LOGGER.warn("Problem while adding the user", e);
				request.setAttribute("userAddError", true);
				request.setAttribute("userAddErrorMessage", e.getMessage());
			}
		} else {
			LOGGER.info("No user add");
		}
		return "/jsp/pages/users/add-modify-user.jsp";
	}

	@Override
	public String getID() {
		return "add-user";
	}

	@Override
	public boolean isTemplateView() {
		return true;
	}

	@Override
	public boolean isSecured() {
		return true;
	}

	@Override
	public ActionCategory getCategory() {
		return ActionCategory.USER;
	}

}
