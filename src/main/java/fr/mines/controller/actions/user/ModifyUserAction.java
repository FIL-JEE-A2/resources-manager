package fr.mines.controller.actions.user;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.ActionSecurity;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import fr.mines.entitites.User;
import fr.mines.service.ServiceExecutionException;

public class ModifyUserAction extends AbstractFrontAction
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ModifyUserAction.class);

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		//Set the user if there is no current update
		if (rq.isSet("id")) {
			rq.attr("modifyUser", true);
			Long userID = Long.parseLong(rq.param("id"));
			//Set the modified user
			LOGGER.info("Modify the user \"{}\"", rq.param("id"));
			rq.attr("previousUser", userService.get(userID));
			//Update in DB
			if (rq.isSet("userLastName")) {
				LOGGER.info("Will update the user \"{}\"", rq.param("id"));
				User updateUser = new User(rq.param("userFirstName"), rq.param("userLastName"),
						rq.param("userEmail"), rq.param("userPhone"), rq.param("userLogin"),
						rq.param("userPassword"), Boolean.parseBoolean(rq.param("userIsAdmin")));
				try {
					userService.update(userID, updateUser);
					rq.attr("userModified", true);
					rq.attr("userModifiedName",
							updateUser.getFirstName() + " " + updateUser.getLastName() + " (" + updateUser.getLogin() + ")");
				} catch (ServiceExecutionException e) {
					LOGGER.warn("Problem while modify the user", e);
					rq.attr("userModifyError", true);
					rq.attr("userModifyErrorMessage", e.getMessage());
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
	public ActionSecurity getSecurityLevel() {
		return ActionSecurity.ADMIN;
	}
	
	@Override
	public ActionCategory getCategory() {
		return ActionCategory.USER;
	}

}
