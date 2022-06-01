package ca.ntro.cards.arraylist.models;

import ca.ntro.cards.arraylist.frontend.views.ArraylistProcedureSettingsView;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureDrawingOptions;
import ca.ntro.cards.models.ProcedureSettingsModel;

public class ArraylistProcedureSettingsModel extends ProcedureSettingsModel<ArraylistProcedureSettingsView, 
                                                                       ArraylistProcedureDrawingOptions> 

       implements ArraylistProcedureDrawingOptions {

	@Override
	public ArraylistProcedureDrawingOptions drawingOptions() {
		return this;
	}

	@Override
	public boolean useFourCardColors() {
		return getUseFourCardColors();
	}

}
