package ca.ntro.cards.fusionsort.models;

import ca.ntro.cards.fusionsort.frontend.views.FusionsortEfficiencySettingsView;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortEfficiencyDrawingOptions;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;

public class FusionsortEfficiencySettingsModel extends EfficiencySettingsModel<FusionsortEfficiencySettingsView, 
                                                                         FusionsortEfficiencyDrawingOptions> 

             implements FusionsortEfficiencyDrawingOptions {

	@Override
	public FusionsortEfficiencyDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

	@Override
	public void displayOn(FusionsortEfficiencySettingsView settingsView) {
	}

}
