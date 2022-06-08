package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.common.frontend.views.CommonSettingsView;
import ca.ntro.cards.common.messages.MsgToggleUseFourCardColors;
import javafx.scene.control.ToggleButton;

public abstract class ProcedureSettingsView extends CommonSettingsView {

	protected abstract ToggleButton useFourCardColorsToggleButton();
	protected abstract ToggleButton useTwoTimeSpeedToggleButton();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);

		initializeUseFourCardColorsToggleButton();
		initializeUseTwoTimeSpeedToggleButton();
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
	private void initializeUseTwoTimeSpeedToggleButton() {
		ToggleButton button = useTwoTimeSpeedToggleButton();

	}
	public void displayUseFourCardColors(boolean useFourCardColors) {
		ToggleButton button = useFourCardColorsToggleButton();
		if(button != null) {
			button.setSelected(useFourCardColors);
		}
	}
}
