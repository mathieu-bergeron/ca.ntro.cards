package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class DashboardView extends ViewFx {
	
	protected abstract Label fpsLabel();

	protected abstract Button settingsButton();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		settingsButton().setFocusTraversable(false);

	}

	public void displayFps(String fps) {
		fpsLabel().setText(fps);
	}


}
