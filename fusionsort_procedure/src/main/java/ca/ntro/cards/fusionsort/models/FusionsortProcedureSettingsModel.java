package ca.ntro.cards.fusionsort.models;

import ca.ntro.cards.fusionsort.frontend.views.FusionsortProcedureSettingsView;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortProcedureDrawingOptions;
import ca.ntro.cards.models.ProcedureSettingsModel;

public class FusionsortProcedureSettingsModel extends ProcedureSettingsModel<FusionsortProcedureSettingsView, 
                                                                       FusionsortProcedureDrawingOptions> 

       implements FusionsortProcedureDrawingOptions {

	@Override
	public FusionsortProcedureDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

}
