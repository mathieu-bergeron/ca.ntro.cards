package ca.ntro.cards.demo.models;

import ca.ntro.cards.demo.models.world2d.DemoEfficiencyDrawingOptions;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;

public class DemoEfficiencySettingsModel extends EfficiencySettingsModel<DemoEfficiencyDrawingOptions> implements DemoEfficiencyDrawingOptions {

	@Override
	public DemoEfficiencyDrawingOptions drawingOptions() {
		return this;
	}

}
