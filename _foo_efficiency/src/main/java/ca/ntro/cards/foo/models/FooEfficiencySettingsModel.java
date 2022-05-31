package ca.ntro.cards.foo.models;

import ca.ntro.cards.foo.frontend.views.FooEfficiencySettingsView;
import ca.ntro.cards.foo.models.world2d.FooEfficiencyDrawingOptions;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;

public class FooEfficiencySettingsModel extends EfficiencySettingsModel<FooEfficiencySettingsView, 
                                                                         FooEfficiencyDrawingOptions> 

             implements FooEfficiencyDrawingOptions {

	@Override
	public FooEfficiencyDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

	@Override
	public void displayOn(FooEfficiencySettingsView settingsView) {
	}

}
