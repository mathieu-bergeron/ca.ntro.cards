package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.common.messages.MsgStopExecutionReplay;
import ca.ntro.cards.frontend.events.EvtStartExecutionReplay;
import ca.ntro.cards.messages.MsgExecutionFastForwardToLastStep;
import ca.ntro.cards.messages.MsgExecutionRewindToFirstStep;
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

	protected abstract Button rewindButton();

	protected abstract Button fastForwardButton();


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		EvtStartExecutionReplay           evtStartCodeExecution   = NtroApp.newEvent(EvtStartExecutionReplay.class);
		MsgStopExecutionReplay            msgStopCodeExecution    = NtroApp.newMessage(MsgStopExecutionReplay.class);
		MsgExecutionStepBack              msgExecutionStepBack    = NtroApp.newMessage(MsgExecutionStepBack.class);
		MsgExecutionStepForward           msgExecutionStepForward = NtroApp.newMessage(MsgExecutionStepForward.class);
		MsgExecutionRewindToFirstStep     msgRewind               = NtroApp.newMessage(MsgExecutionRewindToFirstStep.class);
		MsgExecutionFastForwardToLastStep msgFastForward          = NtroApp.newMessage(MsgExecutionFastForwardToLastStep.class);
		
		playButton().setOnAction(evtFx -> {

			evtStartCodeExecution.trigger();

		});

		pauseButton().setOnAction(evtFx -> {

			msgStopCodeExecution.send();

		});

		oneStepButton().setOnAction(evtFx -> {
			
			msgExecutionStepForward.send();

		});
		
		backStepButton().setOnAction(evtFx -> {
			
			msgExecutionStepBack.send();
			
		});
		
		if(rewindButton() != null) {
			rewindButton().setOnAction(evtFx -> {
				msgRewind.send();
			});
		}
		
		if(fastForwardButton() != null) {
			fastForwardButton().setOnAction(evtFx -> {
				msgFastForward.send();
			});
		}

	}

	public void disableExecutionButtons() {
		playButton().setDisable(true);
		pauseButton().setDisable(true);
		oneStepButton().setDisable(true);
		fastForwardButton().setDisable(true);
		rewindButton().setDisable(true);
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
