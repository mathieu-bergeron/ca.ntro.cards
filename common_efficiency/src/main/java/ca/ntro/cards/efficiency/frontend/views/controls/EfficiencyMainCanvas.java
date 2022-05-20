package ca.ntro.cards.efficiency.frontend.views.controls;

import ca.ntro.cards.common.frontend.views.controls.CommonMainCanvas;
import ca.ntro.cards.efficiency.models.world2d.EfficiencyDrawingOptions;
import ca.ntro.cards.efficiency.models.world2d.EfficiencyObject2d;
import ca.ntro.cards.efficiency.models.world2d.EfficiencyWorld2d;

public class EfficiencyMainCanvas<OBJECT2D extends EfficiencyObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                  WORLD2D  extends EfficiencyWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                  OPTIONS  extends EfficiencyDrawingOptions> 
       
       extends CommonMainCanvas<OBJECT2D, WORLD2D, OPTIONS> {

}
