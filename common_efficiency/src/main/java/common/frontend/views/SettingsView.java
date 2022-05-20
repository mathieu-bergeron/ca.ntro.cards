package common.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.core.stream.Stream;
import common.frontend.events.EvtHideMenu;
import common.frontend.events.EvtQuit;
import common.messages.MsgToggleUseFourCardColors;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class SettingsView extends ViewFx {
	
	protected abstract Stream<Pane> spaces();
	
	protected abstract Button quitButton();

	protected abstract ToggleButton useFourCardColorsToggleButton();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		initializeSpaces();
		initializeQuitButton();
		initializeUseFourCardColorsToggleButton();

	}

	private void initializeUseFourCardColorsToggleButton() {
		MsgToggleUseFourCardColors msgToggleUseFourCardColors = NtroApp.newMessage(MsgToggleUseFourCardColors.class);
		
		ToggleButton button = useFourCardColorsToggleButton();
		if(button != null) {
			button.setOnAction(evtFx -> {
				msgToggleUseFourCardColors.send();
			});
		}
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

	public void displayUseFourCardColors(boolean useFourCardColors) {
		ToggleButton button = useFourCardColorsToggleButton();
		if(button != null) {
			button.setSelected(useFourCardColors);
		}
	}

}
