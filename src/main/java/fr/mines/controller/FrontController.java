package fr.mines.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test front controller
 */
@WebServlet(urlPatterns = "/pages/*")
public class FrontController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(FrontController.class);

	private static final long serialVersionUID = 1L;

	// ======== PAGES ============
	private static final Map<String, String> pages = new HashMap<>();

	static {
		pages.put("login", "/jsp/pages/login.jsp");
	}

	public FrontController() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionName = this.getActionName(request.getPathInfo());
		String pageUrl = pages.get(actionName);
		request.setAttribute("pageUrl", pageUrl);
		executerAction(actionName, request, response);
		getServletContext().getRequestDispatcher("/jsp/common/mainview.jsp").forward(request, response);
	}

	private void executerAction(String actionId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (actionId) {
		case "login":
			String userID = request.getParameter("userId");
			System.out.println(userID);
			String userPassword = request.getParameter("userPassword");
			if ("mathieu".equals(userID) && "admin".equals(userPassword)) {
				request.setAttribute("loginFailed", false);
			} else {
				request.setAttribute("loginFailed", true);
			}
			break;
		}
	}

	/**
	 * To get the action name from a path
	 * @param pathInfo the complete path to analyze
	 * @return the action from the path, or null if action was not found
	 */
	private String getActionName(String pathInfo) {
		if (pathInfo != null) {
			if (pathInfo.startsWith("/"))
				pathInfo = pathInfo.substring(1, pathInfo.length());
		}
		return pathInfo;
	}
}
