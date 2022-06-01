package ca.ntro.cards.arraylist.models;

import ca.ntro.cards.arraylist.frontend.views.ArraylistEfficiencySettingsView;
import ca.ntro.cards.arraylist.models.world2d.ArraylistEfficiencyDrawingOptions;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;

public class ArraylistEfficiencySettingsModel extends EfficiencySettingsModel<ArraylistEfficiencySettingsView, 
                                                                         ArraylistEfficiencyDrawingOptions> 

             implements ArraylistEfficiencyDrawingOptions {

	@Override
	public ArraylistEfficiencyDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

	@Override
	public void displayOn(ArraylistEfficiencySettingsView settingsView) {
	}

}
