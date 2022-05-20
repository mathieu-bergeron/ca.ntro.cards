package ca.ntro.cards.models;

import ca.ntro.app.models.Model;
import common.frontend.views.DashboardView;

public class DashboardModel implements Model {
	
	private long numberOfSteps = 0;
	private int currentStep = 0;
	private int numberOfCards = 0;

	public long getNumberOfSteps() {
		return numberOfSteps;
	}

	public void setNumberOfSteps(long numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public int getNumberOfCards() {
		return numberOfCards;
	}

	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}

	public void incrementSimpleOperations() {
		numberOfSteps++;
	}

	public void displayOn(DashboardView dashboardView) {
		dashboardView.displayNumberOfCards(numberOfCards);
		dashboardView.displayCurrentStep(currentStep);
		dashboardView.displayNumberOfSteps(numberOfSteps);
	}

	public void initialize() {
		numberOfSteps = 0;
	}

}
