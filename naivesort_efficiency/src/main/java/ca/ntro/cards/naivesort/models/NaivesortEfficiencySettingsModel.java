package ca.ntro.cards.naivesort.models;

import ca.ntro.cards.naivesort.frontend.views.NaivesortEfficiencySettingsView;
import ca.ntro.cards.naivesort.models.world2d.NaivesortEfficiencyDrawingOptions;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;

public class NaivesortEfficiencySettingsModel extends EfficiencySettingsModel<NaivesortEfficiencySettingsView, 
                                                                         NaivesortEfficiencyDrawingOptions> 

             implements NaivesortEfficiencyDrawingOptions {

	@Override
	public NaivesortEfficiencyDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

	@Override
	public void displayOn(NaivesortEfficiencySettingsView settingsView) {
	}

}
