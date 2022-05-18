package ca.ntro.cards.demo.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.demo.messages.MsgUnlockThread;
import ca.ntro.cards.frontend.views.DashboardView;
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
	private Button menuButton;

	@FXML
	private Button playButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("fpsLabel", fpsLabel);
		Ntro.assertNotNull("simpleOperationsLabel", simpleOperationsLabel);
		Ntro.assertNotNull("menuButton", menuButton);
		Ntro.assertNotNull("playButton", playButton);
		
		super.initialize(location, resources);
		
		
		
		MsgUnlockThread msgUnlockThread = NtroApp.newMessage(MsgUnlockThread.class);
		
		playButton.setOnAction(evtFx -> {

			msgUnlockThread.send();
			
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

}
