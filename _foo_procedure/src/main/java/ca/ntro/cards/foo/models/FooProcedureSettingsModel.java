package ca.ntro.cards.foo.models;

import ca.ntro.cards.foo.frontend.views.FooProcedureSettingsView;
import ca.ntro.cards.foo.models.world2d.FooProcedureDrawingOptions;
import ca.ntro.cards.models.ProcedureSettingsModel;

public class FooProcedureSettingsModel extends ProcedureSettingsModel<FooProcedureSettingsView, 
                                                                       FooProcedureDrawingOptions> 

       implements FooProcedureDrawingOptions {

	@Override
	public FooProcedureDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

}
