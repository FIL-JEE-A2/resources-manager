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

public class ModifyUserAction implements FrontActionI {
	private static final Logger LOGGER = LoggerFactory.getLogger(ModifyUserAction.class);

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Set the user if there is no current update
		if (request.getParameter("id") != null) {
			request.setAttribute("modifyUser", true);
			Long userID = Long.parseLong(request.getParameter("id"));
			//Set the modified user
			LOGGER.info("Modify the user \"{}\"", request.getParameter("id"));
			User user = UserService.getInstance().get(userID);
			request.setAttribute("previousUser", user);
			//Update in DB
			if (request.getParameter("userLastName") != null) {
				LOGGER.info("Will update the user \"{}\"", request.getParameter("id"));
				User updateUser = new User(request.getParameter("userFirstName"), request.getParameter("userLastName"),
						request.getParameter("userEmail"), request.getParameter("userPhone"), request.getParameter("userLogin"),
						request.getParameter("userPassword"), Boolean.parseBoolean(request.getParameter("userIsAdmin")));
				try {
					UserService.getInstance().update(userID, updateUser);
					request.setAttribute("userModified", true);
					request.setAttribute("userModifiedName",
							updateUser.getFirstName() + " " + updateUser.getLastName() + " (" + updateUser.getLogin() + ")");
				} catch (ServiceExecutionException e) {
					LOGGER.warn("Problem while modify the user", e);
					request.setAttribute("userModifyError", true);
					request.setAttribute("userModifyErrorMessage", e.getMessage());
				}
			}
		}
		return "/jsp/pages/users/add-modify-user.jsp";
	}

	@Override
	public String getID() {
		return "modify-user";
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
