package ca.ntro.cards.demo.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.demo.messages.MsgUnlockThread;
import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.core.initialization.Ntro;
import javafx.application.Platform;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("fpsLabel", fpsLabel);
		Ntro.assertNotNull("simpleOperationsLabel", simpleOperationsLabel);
		Ntro.assertNotNull("numberOfCardsLabel", numberOfCardsLabel);
		Ntro.assertNotNull("menuButton", menuButton);
		Ntro.assertNotNull("playButton", playButton);
		
		super.initialize(location, resources);
		
		
		
		MsgUnlockThread msgUnlockThread = NtroApp.newMessage(MsgUnlockThread.class);
		
		playButton.setOnAction(evtFx -> {
			
			new Timer().scheduleAtFixedRate(
					new TimerTask() {
						@Override
						public void run() {
							Platform.runLater(() -> {
								msgUnlockThread.send();
							});
						}
					} , 0, 100);
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
