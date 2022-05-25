package ca.ntro.cards.common.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.common.frontend.events.EvtShowMenu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class CommonDashboardView extends ViewFx {

	
	protected abstract Label fpsLabel();

	protected abstract Button menuButton();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EvtShowMenu evtShowMenu = NtroApp.newEvent(EvtShowMenu.class);

		if(menuButton() != null) {
			menuButton().setFocusTraversable(false);

			menuButton().setOnAction(evtFx -> {
				evtShowMenu.trigger();
			});
		}
	}

	public void displayFps(String fps) {
		if(fpsLabel() != null) {
			fpsLabel().setText(fps);
		}
	}

	public void clearTestCases() {
	}

	public void addTestCase(String testCase) {
	}



}
