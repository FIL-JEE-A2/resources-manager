package fr.mines.controller.actions.demo;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.ActionSecurity;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import fr.mines.persistence.JPAUtils;

/**
 * Action that create initialization actions
 */
public class InitDemoAction extends AbstractFrontAction {

	@Override
	public String handle(HttpServletRequestDecorator request, HttpServletResponse response) throws Exception {
		if (request.isSet("create")) {
			Scanner scan = new Scanner(this.getClass().getResourceAsStream("/Demo.sql"), "utf-8");
			StringBuilder sb = new StringBuilder();
			while (scan.hasNextLine()) {
				sb.append(scan.nextLine()).append("\n");
			}
			scan.close();
			EntityManager entityManager = JPAUtils.getEntityManager();
			entityManager.getTransaction().begin();
			Query initQuery = entityManager.createNativeQuery(sb.toString());
			initQuery.executeUpdate();
			entityManager.getTransaction().commit();
			request.attr("created", true);
		}
		return "/jsp/pages/create-demo.jsp";
	}

	@Override
	public String getID() {
		return "demo-init";
	}

	@Override
	public boolean isTemplateView() {
		return false;
	}

	@Override
	public ActionSecurity getSecurityLevel() {
		return ActionSecurity.NONE;
	}

	@Override
	public ActionCategory getCategory() {
		return ActionCategory.NONE;
	}

}
