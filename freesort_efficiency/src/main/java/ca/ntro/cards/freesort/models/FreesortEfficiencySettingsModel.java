package ca.ntro.cards.freesort.models;

import ca.ntro.cards.freesort.frontend.views.FreesortEfficiencySettingsView;
import ca.ntro.cards.freesort.models.world2d.FreesortEfficiencyDrawingOptions;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;

public class FreesortEfficiencySettingsModel extends EfficiencySettingsModel<FreesortEfficiencySettingsView, 
                                                                         FreesortEfficiencyDrawingOptions> 

             implements FreesortEfficiencyDrawingOptions {

	@Override
	public FreesortEfficiencyDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

	@Override
	public void displayOn(FreesortEfficiencySettingsView settingsView) {
	}

}
