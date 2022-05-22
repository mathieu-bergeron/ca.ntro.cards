package ca.ntro.cards.models;

import ca.ntro.cards.common.models.CommonSettingsModel;
import ca.ntro.cards.frontend.views.ProcedureSettingsView;
import ca.ntro.cards.models.world2d.ProcedureDrawingOptions;

public abstract class ProcedureSettingsModel<SETTINGS_VIEW extends ProcedureSettingsView, 
                                             OPTIONS extends ProcedureDrawingOptions> 


       extends CommonSettingsModel<SETTINGS_VIEW, OPTIONS> {

	public void displayOn(ProcedureSettingsView settingsView) {
		settingsView.displayUseFourCardColors(getUseFourCardColors());
	}

}
