package ca.ntro.cards.models;

import ca.ntro.app.models.Model;
import common.frontend.views.SettingsView;
import common.models.world2d.CommonDrawingOptions;

public class SettingsModel implements Model, CommonDrawingOptions {
	
	private boolean useFourCardColors = true;

	public boolean getUseFourCardColors() {
		return useFourCardColors;
	}

	public void setUseFourCardColors(boolean useFourCardColors) {
		this.useFourCardColors = useFourCardColors;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}
	
	
	public void toggleUseFourCardColors() {
		this.useFourCardColors = !this.useFourCardColors;
	}

	public void displayOn(SettingsView menuView) {
		menuView.displayUseFourCardColors(useFourCardColors);
		
	}

}
