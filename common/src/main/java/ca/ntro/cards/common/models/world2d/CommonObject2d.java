package ca.ntro.cards.common.models.world2d;

import ca.ntro.app.world2d.Object2dFx;

public abstract class   CommonObject2d<OBJECT2D extends CommonObject2d<OBJECT2D, WORLD2D, OPTIONS>, 
                                       WORLD2D  extends CommonWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                       OPTIONS  extends CommonDrawingOptions>

                extends Object2dFx<OBJECT2D, 
                                   WORLD2D, 
                                   OPTIONS> {

}
