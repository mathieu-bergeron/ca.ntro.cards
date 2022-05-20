package ca.ntro.cards.common.models;

import ca.ntro.app.models.Model;
import ca.ntro.cards.common.frontend.views.CommonSettingsView;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;

public class CommonSettingsModel implements Model, CommonDrawingOptions {
	
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

	public void displayOn(CommonSettingsView menuView) {
		menuView.displayUseFourCardColors(useFourCardColors);
		
	}

}
