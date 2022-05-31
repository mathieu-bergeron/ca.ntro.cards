package ca.ntro.cards.freesort.models;

import ca.ntro.cards.freesort.frontend.views.FreesortProcedureSettingsView;
import ca.ntro.cards.freesort.models.world2d.FreesortProcedureDrawingOptions;
import ca.ntro.cards.models.ProcedureSettingsModel;

public class FreesortProcedureSettingsModel extends ProcedureSettingsModel<FreesortProcedureSettingsView, 
                                                                       FreesortProcedureDrawingOptions> 

       implements FreesortProcedureDrawingOptions {

	@Override
	public FreesortProcedureDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

}
