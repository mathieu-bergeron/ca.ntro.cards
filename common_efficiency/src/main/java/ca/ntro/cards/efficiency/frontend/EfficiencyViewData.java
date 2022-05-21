package ca.ntro.cards.efficiency.frontend;

import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.efficiency.models.world2d.EfficiencyDrawingOptions;
import ca.ntro.cards.efficiency.models.world2d.EfficiencyObject2d;
import ca.ntro.cards.efficiency.models.world2d.EfficiencyWorld2d;

public abstract class   EfficiencyViewData<OBJECT2D extends EfficiencyObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                          WORLD2D  extends EfficiencyWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                          OPTIONS  extends EfficiencyDrawingOptions> 

                extends CommonViewData<OBJECT2D, WORLD2D, OPTIONS> {

}
