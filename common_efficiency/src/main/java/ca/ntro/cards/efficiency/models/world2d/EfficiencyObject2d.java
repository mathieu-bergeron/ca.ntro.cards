package ca.ntro.cards.efficiency.models.world2d;

import ca.ntro.cards.common.models.world2d.CommonObject2d;

public abstract class EfficiencyObject2d<OBJECT2D extends EfficiencyObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                         WORLD2D extends EfficiencyWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                         OPTIONS extends EfficiencyDrawingOptions> 

      extends  CommonObject2d<OBJECT2D, WORLD2D, OPTIONS> {

}
