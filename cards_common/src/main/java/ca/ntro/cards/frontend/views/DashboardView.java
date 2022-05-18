package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.frontend.events.EvtShowMenu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class DashboardView extends ViewFx {

	protected abstract Label simpleOperationsLabel();

	protected abstract Label numberOfCardsLabel();
	
	protected abstract Label fpsLabel();

	protected abstract Button menuButton();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		menuButton().setFocusTraversable(false);
		
		EvtShowMenu evtShowMenu = NtroApp.newEvent(EvtShowMenu.class);

		menuButton().setOnAction(evtFx -> {
			evtShowMenu.trigger();
		});

	}

	public void displayFps(String fps) {
		fpsLabel().setText(fps);
	}

	public void displaySimpleOperations(long simpleOperations) {
		simpleOperationsLabel().setText(String.valueOf(simpleOperations));
	}

	public void displayNumberOfCards(int numberOfCards) {
		numberOfCardsLabel().setText(String.valueOf(numberOfCards));
	}

}
