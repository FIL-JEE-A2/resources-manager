package fr.mines.controller;

import fr.mines.RMConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Test front controller
 */
@WebServlet(urlPatterns = "/pages/*")
public class FrontController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(FrontController.class);

	private static final long serialVersionUID = 1L;

	private final ActionManager actionManager = new ActionManager();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequestDecorator rq = new HttpServletRequestDecorator(request);
		try
		{
			FrontActionI action = actionManager.getAction(request.getPathInfo());
			LOGGER.info("Execute the action {}", action.getID());
			//Check security
			if (!action.isSecured() || rq.connectedUser() != null)
			{
				String dispatchUrl = action.handle(rq, response);
				ActionCategory category = action.getCategory();

				if (category.getTabId() != null) rq.attr(category.getTabId(), true);
				if (action.isTemplateView())
				{
					rq.attr("pageUrl", dispatchUrl);
					getServletContext().getRequestDispatcher(RMConstant.MAIN_TEMPLATE_JSP).forward(request, response);
				}
				else getServletContext().getRequestDispatcher(dispatchUrl).forward(request, response);
			}
			else
			{
				LOGGER.info("The action {} is not authorized, redirect to login page", action.getID());
				response.sendRedirect(request.getContextPath() + "/pages/login?unauthorizedAction=true");
			}
		} catch (Exception e) {
			handleError(e, request, response);
		}
	}

	/**
	 * Handle an exception and redirect to a dedicated error page (/jsp/pages/error-report.jps)
	 * @param e the exception to handle
	 * @param request the request to forward
	 * @param response the response to forward
	 */
	private void handleError(Exception e, HttpServletRequest request, HttpServletResponse response)
	{
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
