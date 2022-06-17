package ca.ntro.cards.intro.models;

import ca.ntro.cards.intro.frontend.views.IntroProcedureSettingsView;
import ca.ntro.cards.intro.models.world2d.IntroProcedureDrawingOptions;
import ca.ntro.cards.models.ProcedureSettingsModel;

public class IntroProcedureSettingsModel extends ProcedureSettingsModel<IntroProcedureSettingsView, 
                                                                       IntroProcedureDrawingOptions> 

       implements IntroProcedureDrawingOptions {

	@Override
	public IntroProcedureDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

}
