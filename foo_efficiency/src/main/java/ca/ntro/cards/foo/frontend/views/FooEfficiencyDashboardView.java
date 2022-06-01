package ca.ntro.cards.foo.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.efficiency.frontend.views.EfficiencyDashboardView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FooEfficiencyDashboardView extends EfficiencyDashboardView {

	@FXML
	private Label fpsLabel;

	@FXML
	private Button menuButton;

	@FXML
	private Button messageButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("fpsLabel", fpsLabel);
		Ntro.assertNotNull("menuButton", menuButton);
		Ntro.assertNotNull("messageButton", messageButton);

		super.initialize(location, resources);

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
	protected Button messageButton() {
		return messageButton;
	}
}

