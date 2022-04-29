package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;
import javafx.scene.control.Label;

public abstract class DashboardView extends ViewFx {
	
	protected abstract Label getFpsLabel();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void displayFps(String fps) {
		getFpsLabel().setText(fps);
	}


}
