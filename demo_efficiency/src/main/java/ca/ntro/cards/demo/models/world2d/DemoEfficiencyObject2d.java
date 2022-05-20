package ca.ntro.cards.demo.models.world2d;

import ca.ntro.cards.efficiency.models.world2d.EfficiencyObject2d;

public abstract class DemoEfficiencyObject2d <OBJECT2D extends DemoEfficiencyObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                              WORLD2D extends DemoEfficiencyWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                              OPTIONS extends DemoEfficiencyDrawingOptions> 

       extends EfficiencyObject2d<OBJECT2D, WORLD2D, OPTIONS> {

}
