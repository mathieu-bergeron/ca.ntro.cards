package ca.ntro.cards.naivesort.models;

import ca.ntro.cards.naivesort.frontend.views.NaivesortProcedureSettingsView;
import ca.ntro.cards.naivesort.models.world2d.NaivesortProcedureDrawingOptions;
import ca.ntro.cards.models.ProcedureSettingsModel;

public class NaivesortProcedureSettingsModel extends ProcedureSettingsModel<NaivesortProcedureSettingsView, 
                                                                       NaivesortProcedureDrawingOptions> 

       implements NaivesortProcedureDrawingOptions {

	@Override
	public NaivesortProcedureDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

}
