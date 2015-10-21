package fr.mines.controller;

/**
 * Action category
 */
public enum ActionCategory {
	HOME("homeCategory"), RESERVATION("reservationCategory"), RESSOURCE("ressourceCategory"), USER("userCategory"), RESSOURCE_TYPE(
			"ressourceTypeCateogory"), NONE(null);
	private String tabId;

	ActionCategory(String tabIdP) {
		this.tabId = tabIdP;
	}

	public String getTabId() {
		return tabId;
	}
}