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
		addAction(new LoginAction());
		addAction(new HomeAction());
		addAction(new DisconnectAction());
		addAction(new ListUserAction());
		addAction(new AddUserAction());
		addAction(new ModifyUserAction());
		addAction(new DeleteUserAction());
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
			//TODO : log + error page
			e.printStackTrace();
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
