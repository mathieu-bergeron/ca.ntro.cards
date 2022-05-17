package ca.ntro.cards.demo.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("fpsLabel", fpsLabel);
		Ntro.assertNotNull("simpleOperationsLabel", simpleOperationsLabel);
		Ntro.assertNotNull("menuButton", menuButton);
		
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
	protected Label simpleOperationsLabel() {
		return simpleOperationsLabel;
	}

}
