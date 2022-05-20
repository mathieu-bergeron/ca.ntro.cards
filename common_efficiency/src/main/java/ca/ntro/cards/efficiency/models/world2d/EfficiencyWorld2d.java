package ca.ntro.cards.efficiency.models.world2d;

import ca.ntro.cards.common.models.world2d.CommonWorld2d;

public class EfficiencyWorld2d <OBJECT2D extends EfficiencyObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                WORLD2D extends EfficiencyWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                OPTIONS extends EfficiencyDrawingOptions> 

       extends CommonWorld2d<OBJECT2D, WORLD2D, OPTIONS> {

}
