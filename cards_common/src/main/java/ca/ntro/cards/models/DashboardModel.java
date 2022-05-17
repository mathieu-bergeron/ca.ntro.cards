package ca.ntro.cards.models;

import ca.ntro.app.models.Model;
import ca.ntro.cards.frontend.views.DashboardView;

public class DashboardModel implements Model {
	
	private long simpleOperations = 0;

	public long getSimpleOperations() {
		return simpleOperations;
	}

	public void setSimpleOperations(long simpleOperations) {
		this.simpleOperations = simpleOperations;
	}

	public void displayOn(DashboardView dashboardView) {
		dashboardView.displaySimpleOperations(simpleOperations);
	}

}
