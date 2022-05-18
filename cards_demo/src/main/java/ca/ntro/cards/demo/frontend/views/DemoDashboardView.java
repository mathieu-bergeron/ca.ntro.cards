package ca.ntro.cards.demo.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.frontend.events.EvtStartCodeExecution;
import ca.ntro.cards.frontend.events.EvtStopCodeExecution;
import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.cards.messages.MsgExecuteCodeOneStep;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DemoDashboardView extends DashboardView {
	
	
	@FXML
	private Label fpsLabel;

	@FXML
	private Label simpleOperationsLabel;

	@FXML
	private Label numberOfCardsLabel;

	@FXML
	private Button menuButton;

	@FXML
	private Button playButton;

	@FXML
	private Button pauseButton;

	@FXML
	private Button oneStepButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("fpsLabel", fpsLabel);
		Ntro.assertNotNull("simpleOperationsLabel", simpleOperationsLabel);
		Ntro.assertNotNull("numberOfCardsLabel", numberOfCardsLabel);
		Ntro.assertNotNull("menuButton", menuButton);
		Ntro.assertNotNull("playButton", playButton);
		Ntro.assertNotNull("pauseButton", pauseButton);
		Ntro.assertNotNull("oneStepButton", oneStepButton);
		
		super.initialize(location, resources);
		
		EvtStartCodeExecution evtStartCodeExecution = NtroApp.newEvent(EvtStartCodeExecution.class);
		EvtStopCodeExecution evtStopCodeExecution = NtroApp.newEvent(EvtStopCodeExecution.class);
		MsgExecuteCodeOneStep msgExecuteCodeOneStep = NtroApp.newMessage(MsgExecuteCodeOneStep.class);
		
		playButton.setOnAction(evtFx -> {

			evtStartCodeExecution.trigger();

		});

		pauseButton.setOnAction(evtFx -> {

			evtStopCodeExecution.trigger();

		});

		oneStepButton.setOnAction(evtFx -> {
			
			msgExecuteCodeOneStep.send();

		});
	}

	@Override
	protected Label fpsLabel() {
		return fpsLabel;
	}

	@Override
	protected Button menuButton() {
		return menuButton;
	}

	@Override
	protected Label simpleOperationsLabel() {
		return simpleOperationsLabel;
	}

	@Override
	protected Label numberOfCardsLabel() {
		return numberOfCardsLabel;
	}

}
