package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.frontend.events.EvtStartCodeExecution;
import ca.ntro.cards.frontend.events.EvtStopCodeExecution;
import ca.ntro.cards.messages.MsgExecutionStepBack;
import ca.ntro.cards.messages.MsgExecutionStepForward;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class ProcedureReplayView extends ViewFx {

	protected abstract Label numberOfStepsLabel();

	protected abstract Label currentStepLabel();

	protected abstract Label numberOfCardsLabel();

	protected abstract Button playButton();

	protected abstract Button pauseButton();

	protected abstract Button oneStepButton();

	protected abstract Button backStepButton();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

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

	public void disableExecutionButtons() {
		playButton().setDisable(true);
		pauseButton().setDisable(true);
		oneStepButton().setDisable(true);
	}

	public void displayNumberOfSteps(String numberOfSteps) {
		if(numberOfStepsLabel() != null) {
			numberOfStepsLabel().setText(numberOfSteps);
		}
	}

	public void displayCurrentStep(String currentStep) {
		if(currentStepLabel() != null) {
			currentStepLabel().setText(currentStep);
		}
	}

	public void displayNumberOfCards(String numberOfCards) {
		if(numberOfCardsLabel() != null) {
			numberOfCardsLabel().setText(numberOfCards);
		}
	}



}
