package ca.ntro.cards.demo.models;

import ca.ntro.cards.demo.models.world2d.DemoDrawingOptions;
import ca.ntro.cards.models.ProcedureSettingsModel;

public class DemoSettingsModel extends ProcedureSettingsModel<DemoDrawingOptions> implements DemoDrawingOptions {

	@Override
	public DemoDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

}
