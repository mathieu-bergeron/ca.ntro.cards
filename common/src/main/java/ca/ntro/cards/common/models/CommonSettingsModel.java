package ca.ntro.cards.common.models;

import ca.ntro.app.models.Model;
import ca.ntro.cards.common.frontend.views.CommonSettingsView;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;

public abstract class CommonSettingsModel<SETTINGS_VIEW extends CommonSettingsView, 
                                          OPTIONS extends CommonDrawingOptions> implements Model {
	
	private boolean useFourCardColors = true;

	public boolean getUseFourCardColors() {
		return useFourCardColors;
	}

	public void setUseFourCardColors(boolean useFourCardColors) {
		this.useFourCardColors = useFourCardColors;
	}
	
	public abstract OPTIONS drawingOptions();

	public void toggleUseFourCardColors() {
		this.useFourCardColors = !this.useFourCardColors;
	}

	public abstract void displayOn(SETTINGS_VIEW settingsView);

}
