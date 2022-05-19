package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.frontend.events.EvtShowMenu;
import ca.ntro.cards.frontend.events.EvtStartCodeExecution;
import ca.ntro.cards.frontend.events.EvtStopCodeExecution;
import ca.ntro.cards.messages.MsgExecutionStepBack;
import ca.ntro.cards.messages.MsgExecutionStepForward;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class DashboardView extends ViewFx {

	protected abstract Label numberOfStepsLabel();

	protected abstract Label currentStepLabel();

	protected abstract Label numberOfCardsLabel();
	
	protected abstract Label fpsLabel();

	protected abstract Button menuButton();

	protected abstract Button playButton();

	protected abstract Button pauseButton();

	protected abstract Button oneStepButton();

	protected abstract Button backStepButton();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		menuButton().setFocusTraversable(false);
		
		EvtShowMenu evtShowMenu = NtroApp.newEvent(EvtShowMenu.class);

		menuButton().setOnAction(evtFx -> {
			evtShowMenu.trigger();
		});

		EvtStartCodeExecution   evtStartCodeExecution   = NtroApp.newEvent(EvtStartCodeExecution.class);
		EvtStopCodeExecution    evtStopCodeExecution    = NtroApp.newEvent(EvtStopCodeExecution.class);
		MsgExecutionStepBack    msgExecutionStepBack    = NtroApp.newMessage(MsgExecutionStepBack.class);
		MsgExecutionStepForward msgExecutionStepForward = NtroApp.newMessage(MsgExecutionStepForward.class);
		
		playButton().setOnAction(evtFx -> {

			evtStartCodeExecution.trigger();

		});

		pauseButton().setOnAction(evtFx -> {

			evtStopCodeExecution.trigger();

		});

		oneStepButton().setOnAction(evtFx -> {
			
			msgExecutionStepForward.send();

		});
		
		backStepButton().setOnAction(evtFx -> {
			
			msgExecutionStepBack.send();
			
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

	public void disableExecutionButtons() {
		playButton().setDisable(true);
		pauseButton().setDisable(true);
		oneStepButton().setDisable(true);
	}


}
