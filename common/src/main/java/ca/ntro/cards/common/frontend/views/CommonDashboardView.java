package ca.ntro.cards.common.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.common.frontend.events.EvtShowMenu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class CommonDashboardView extends ViewFx {

	protected abstract Label numberOfStepsLabel();

	protected abstract Label currentStepLabel();

	protected abstract Label numberOfCardsLabel();
	
	protected abstract Label fpsLabel();

	protected abstract Button menuButton();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		menuButton().setFocusTraversable(false);
		
		EvtShowMenu evtShowMenu = NtroApp.newEvent(EvtShowMenu.class);

		menuButton().setOnAction(evtFx -> {
			evtShowMenu.trigger();
		});
	}

	public void displayFps(String fps) {
		fpsLabel().setText(fps);
	}

	public void displayNumberOfSteps(long numberOfSteps) {
		numberOfStepsLabel().setText(String.valueOf(numberOfSteps));
	}

	public void displayCurrentStep(int currentStep) {
		currentStepLabel().setText(String.valueOf(currentStep));
	}

	public void displayNumberOfCards(int numberOfCards) {
		numberOfCardsLabel().setText(String.valueOf(numberOfCards));
	}



}
