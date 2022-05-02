package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.frontend.events.EvtHideMenu;
import ca.ntro.cards.frontend.events.EvtQuit;
import ca.ntro.core.stream.Stream;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class MenuView extends ViewFx {
	
	protected abstract Stream<Pane> spaces();
	
	protected abstract Button quitButton();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		initializeSpaces();
		initializeQuitButton();

	}

	private void initializeQuitButton() {
		EvtQuit evtQuit = NtroApp.newEvent(EvtQuit.class);

		quitButton().setOnAction(evtFx -> {
			evtQuit.trigger();
		});
	}

	private void initializeSpaces() {
		EvtHideMenu evtHideMenu = NtroApp.newEvent(EvtHideMenu.class);

		spaces().forEach(space -> {
			space.addEventFilter(MouseEvent.MOUSE_CLICKED, evtFx -> {
				evtHideMenu.trigger();
			});
		});
	}

}
