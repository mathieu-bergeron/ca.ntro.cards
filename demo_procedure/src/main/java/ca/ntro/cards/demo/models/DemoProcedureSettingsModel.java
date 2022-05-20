package ca.ntro.cards.demo.models;

import ca.ntro.cards.demo.models.world2d.DemoProcedureDrawingOptions;
import ca.ntro.cards.models.ProcedureSettingsModel;

public class DemoProcedureSettingsModel extends ProcedureSettingsModel<DemoProcedureDrawingOptions> implements DemoProcedureDrawingOptions {

	@Override
	public DemoProcedureDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

}
