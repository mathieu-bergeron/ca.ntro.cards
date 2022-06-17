package ca.ntro.cards.intro.models;

import ca.ntro.cards.intro.frontend.views.IntroEfficiencySettingsView;
import ca.ntro.cards.intro.models.world2d.IntroEfficiencyDrawingOptions;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;

public class IntroEfficiencySettingsModel extends EfficiencySettingsModel<IntroEfficiencySettingsView, 
                                                                         IntroEfficiencyDrawingOptions> 

             implements IntroEfficiencyDrawingOptions {

	@Override
	public IntroEfficiencyDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

	@Override
	public void displayOn(IntroEfficiencySettingsView settingsView) {
	}

}
