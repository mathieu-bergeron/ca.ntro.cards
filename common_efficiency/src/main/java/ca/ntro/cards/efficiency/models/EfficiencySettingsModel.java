package ca.ntro.cards.efficiency.models;

import ca.ntro.cards.common.models.CommonSettingsModel;
import ca.ntro.cards.efficiency.frontend.views.EfficiencySettingsView;
import ca.ntro.cards.efficiency.models.world2d.EfficiencyDrawingOptions;

public abstract class   EfficiencySettingsModel<SETTINGS_VIEW extends EfficiencySettingsView, 
                                                OPTIONS       extends EfficiencyDrawingOptions> 

                extends CommonSettingsModel<SETTINGS_VIEW, OPTIONS> {

}
