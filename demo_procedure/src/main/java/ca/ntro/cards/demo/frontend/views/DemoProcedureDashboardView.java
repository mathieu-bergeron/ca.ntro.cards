package ca.ntro.cards.demo.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.ProcedureDashboardView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DemoProcedureDashboardView extends ProcedureDashboardView {
	
	@FXML
	private Label fpsLabel;

	@FXML
	private Label numberOfStepsLabel;

	@FXML
	private Label currentStepLabel;

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

	@FXML
	private Button backStepButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("fpsLabel", fpsLabel);
		Ntro.assertNotNull("numberOfStepsLabel", numberOfStepsLabel);
		Ntro.assertNotNull("currentStepLabel", currentStepLabel);
		Ntro.assertNotNull("numberOfCardsLabel", numberOfCardsLabel);
		Ntro.assertNotNull("menuButton", menuButton);
		Ntro.assertNotNull("playButton", playButton);
		Ntro.assertNotNull("pauseButton", pauseButton);
		Ntro.assertNotNull("oneStepButton", oneStepButton);
		Ntro.assertNotNull("backStepButton", backStepButton);
		
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
	protected Label numberOfStepsLabel() {
		return numberOfStepsLabel;
	}

	@Override
	protected Label numberOfCardsLabel() {
		return numberOfCardsLabel;
	}

	@Override
	protected Button playButton() {
		return playButton;
	}

	@Override
	protected Button pauseButton() {
		return pauseButton;
	}

	@Override
	protected Button oneStepButton() {
		return oneStepButton;
	}

	@Override
	protected Button backStepButton() {
		return backStepButton;
	}

	@Override
	protected Label currentStepLabel() {
		return currentStepLabel;
	}

}
