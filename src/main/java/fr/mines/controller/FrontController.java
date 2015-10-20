package fr.mines.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.RMConstant;
import fr.mines.controller.actions.DisconnectAction;
import fr.mines.controller.actions.HomeAction;
import fr.mines.controller.actions.LoginAction;
import fr.mines.controller.actions.reservation.AddReservationAction;
import fr.mines.controller.actions.reservation.DeleteReservationAction;
import fr.mines.controller.actions.reservation.ListReservationAction;
import fr.mines.controller.actions.reservation.ModifyReservationAction;
import fr.mines.controller.actions.resource.AddResourceAction;
import fr.mines.controller.actions.resource.DeleteResourceAction;
import fr.mines.controller.actions.resource.ListResourceAction;
import fr.mines.controller.actions.resource.ModifyResourceAction;
import fr.mines.controller.actions.resource_type.AddResourceTypeAction;
import fr.mines.controller.actions.resource_type.DeleteResourceTypeAction;
import fr.mines.controller.actions.resource_type.ListResourceTypeAction;
import fr.mines.controller.actions.resource_type.ModifyResourceTypeAction;
import fr.mines.controller.actions.user.AddUserAction;
import fr.mines.controller.actions.user.DeleteUserAction;
import fr.mines.controller.actions.user.ListUserAction;
import fr.mines.controller.actions.user.ModifyUserAction;
import fr.mines.entitites.User;

/**
 * Test front controller
 */
@WebServlet(urlPatterns = "/pages/*")
public class FrontController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(FrontController.class);

	private static final long serialVersionUID = 1L;

	// ======== PAGES ============
	private static final Map<String, FrontActionI> actions = new HashMap<>();

	static {
		//Login
		addAction(new LoginAction());
		addAction(new DisconnectAction());
		//Home
		addAction(new HomeAction());
		//User
		addAction(new ListUserAction());
		addAction(new AddUserAction());
		addAction(new ModifyUserAction());
		addAction(new DeleteUserAction());
		//Resource
		addAction(new ListResourceAction());
		addAction(new AddResourceAction());
		addAction(new DeleteResourceAction());
		addAction(new ModifyResourceAction());
		//ResourceType
		addAction(new ListResourceTypeAction());
		addAction(new AddResourceTypeAction());
		addAction(new ModifyResourceTypeAction());
		addAction(new DeleteResourceTypeAction());
		//Reservation
		addAction(new ListReservationAction());
		addAction(new AddReservationAction());
		addAction(new ModifyReservationAction());
		addAction(new DeleteReservationAction());
	}

	static void addAction(FrontActionI action) {
		actions.put(action.getID(), action);
	}

	// ===========================

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionId = this.getActionId(request.getPathInfo());
		FrontActionI action = actions.get(actionId);
		if (action != null) {
			executeAction(request, response, action);
		} else {
			LOGGER.info("Unknown action id {}", actionId);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void executeAction(HttpServletRequest request, HttpServletResponse response, FrontActionI action) {
		LOGGER.info("Execute the action {}", action.getID());
		try {
			String dispatchUrl = action.handle(request, response);
			LOGGER.debug(dispatchUrl);
			//Check security
			boolean authorizedAction = true;
			if (action.isSecured()) {
				HttpSession session = request.getSession(true);
				User user = (User) session.getAttribute("user");
				if (user == null) {
					authorizedAction = false;
				}
			}
			if (authorizedAction) {
				ActionCategory category = action.getCategory();
				if (category.getTabId() != null) {
					request.setAttribute(category.getTabId(), true);
				}
				if (action.isTemplateView()) {
					request.setAttribute("pageUrl", dispatchUrl);
					getServletContext().getRequestDispatcher(RMConstant.MAIN_TEMPLATE_JSP).forward(request, response);
				} else {
					getServletContext().getRequestDispatcher(dispatchUrl).forward(request, response);
				}
			} else {
				LOGGER.info("The action {} is not authorized, redirect to login page", action.getID());
				response.sendRedirect(request.getContextPath() + "/pages/login?unauthorizedAction=true");
			}
		} catch (Exception e) {
			LOGGER.warn("Redirect user to error page", e);
			try {
				request.setAttribute("errorType", e.getClass().getName());
				request.setAttribute("errorMessage", e.getMessage());
				getServletContext().getRequestDispatcher("/jsp/pages/error-report.jsp").forward(request, response);
			} catch (Exception e1) {
				LOGGER.error("Couldn't redirect to error page", e1);
			}
		}
	}

	/**
	 * To get the action name from a path
	 * @param pathInfo the complete path to analyze
	 * @return the action from the path, or null if action was not found
	 */
	private String getActionId(String pathInfo) {
		if (pathInfo != null) {
			if (pathInfo.startsWith("/"))
				pathInfo = pathInfo.substring(1, pathInfo.length());
		}
		return pathInfo;
	}
}
