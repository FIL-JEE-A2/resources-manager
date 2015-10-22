package fr.mines.controller;

import javax.servlet.http.HttpServletResponse;

/**
 * Represent an action handled by front controller.
 */
public interface FrontActionI {
	/**
	 * Execute the action, pass the needed parameter to request
	 * @param request the request servlet
	 * @param response the response servlet
	 * @return the JSP path, that will be displayed inside a template if {@link #isTemplateView()} return true
	 * @throws Exception if the action can't be done
	 */
	public String handle(HttpServletRequestDecorator request, HttpServletResponse response) throws Exception;

	/**
	 * @return the action ID
	 */
	public String getID();

	/**
	 * @return if the JSP should be included in main application template
	 */
	public boolean isTemplateView();

	/**
	 * @return the action security level
	 */
	public ActionSecurity getSecurityLevel();

	/**
	 * @return the action category : use to select active tab
	 */
	public ActionCategory getCategory();
}
