package ca.ntro.cards.playground.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PlaygroundDashboardView extends DashboardView {
	
	@FXML
	private Label fpsLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("fpsLabel", fpsLabel);

		super.initialize(location, resources);
	}

	@Override
	protected Label getFpsLabel() {
		return fpsLabel;
	}

}
