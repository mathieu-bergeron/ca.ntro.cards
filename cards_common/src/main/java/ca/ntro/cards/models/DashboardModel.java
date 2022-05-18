package ca.ntro.cards.models;

import ca.ntro.app.models.Model;
import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.core.reflection.object_graph.Initializable;

public class DashboardModel implements Model {
	
	private long simpleOperations = 0;
	private int numberOfCards = 0;

	public long getSimpleOperations() {
		return simpleOperations;
	}

	public void setSimpleOperations(long simpleOperations) {
		this.simpleOperations = simpleOperations;
	}

	public int getNumberOfCards() {
		return numberOfCards;
	}

	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}

	public void incrementSimpleOperations() {
		simpleOperations++;
	}

	public void displayOn(DashboardView dashboardView) {
		dashboardView.displayNumberOfCards(numberOfCards);
		dashboardView.displaySimpleOperations(simpleOperations);
	}

	public void initialize() {
		simpleOperations = 0;
	}

}
