package ca.ntro.cards.playground.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PlaygroundDashboardView extends DashboardView {
	
	@FXML
	private Label fpsLabel;

	@FXML
	private Button settingsButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("fpsLabel", fpsLabel);
		Ntro.assertNotNull("settingsButton", settingsButton);
		
		super.initialize(location, resources);
	}

	@Override
	protected Label fpsLabel() {
		return fpsLabel;
	}

	@Override
	protected Button settingsButton() {
		return settingsButton;
	}

}
