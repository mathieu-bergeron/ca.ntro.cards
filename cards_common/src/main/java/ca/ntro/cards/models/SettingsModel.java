package ca.ntro.cards.models;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.Watchable;
import ca.ntro.cards.models.world2d.CommonDrawingOptions;

public class SettingsModel implements Model, Watchable, CommonDrawingOptions {
	
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

}
