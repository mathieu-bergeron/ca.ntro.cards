package ca.ntro.cards.demo.models;

import ca.ntro.cards.demo.frontend.views.DemoEfficiencySettingsView;
import ca.ntro.cards.demo.models.world2d.DemoEfficiencyDrawingOptions;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;

public class DemoEfficiencySettingsModel extends EfficiencySettingsModel<DemoEfficiencySettingsView, 
                                                                         DemoEfficiencyDrawingOptions> 

             implements DemoEfficiencyDrawingOptions {

	@Override
	public DemoEfficiencyDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

	@Override
	public void displayOn(DemoEfficiencySettingsView settingsView) {
	}

}
