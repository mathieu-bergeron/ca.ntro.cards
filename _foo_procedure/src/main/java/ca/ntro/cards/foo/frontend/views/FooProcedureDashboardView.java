package ca.ntro.cards.foo.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.ProcedureDashboardView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class FooProcedureDashboardView extends ProcedureDashboardView {
	
	@FXML
	private Label fpsLabel;

	@FXML
	private Button menuButton;

	@FXML
	private VBox selectionsContainer;

	@FXML
	private VBox replayControlsContainer;

	@FXML
	private VBox variablesContainer;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("menuButton", menuButton);
		Ntro.assertNotNull("fpsLabel", fpsLabel);
		Ntro.assertNotNull("selectionsContainer", selectionsContainer);
		Ntro.assertNotNull("replayControlsContainer", replayControlsContainer);
		Ntro.assertNotNull("variablesContainer", variablesContainer);
		
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
	protected Pane selectionsContainer() {
		return selectionsContainer;
	}

	@Override
	protected Pane replayControlsContainer() {
		return replayControlsContainer;
	}

	@Override
	protected Pane variablesContainer() {
		return variablesContainer;
	}


}
