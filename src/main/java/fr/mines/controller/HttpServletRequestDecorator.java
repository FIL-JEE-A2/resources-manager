package fr.mines.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.mines.entitites.User;

/**
 * Created by valentin on 20/10/15.
 */
public class HttpServletRequestDecorator {
	private HttpServletRequest request;

	public HttpServletRequestDecorator(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest request() {
		return request;
	}

	public boolean isSet(String param) {
		return request.getParameter(param) != null;
	}

	public String param(String param) {
		return request.getParameter(param);
	}

	public Object attr(String attr) {
		return request.getAttribute(attr);
	}

	public void attr(String attr, Object value) {
		request.setAttribute(attr, value);
	}

	public User connectedUser() {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return user;
		} else return null;
	}
}
