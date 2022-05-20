package ca.ntro.cards.common.frontend.views.controls;

import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.cards.common.models.world2d.CommonObject2d;
import ca.ntro.cards.common.models.world2d.CommonWorld2d;

public class CommonPreviewCanvas <OBJECT2D extends CommonObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                   WORLD2D  extends CommonWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                   OPTIONS  extends CommonDrawingOptions> 

       extends World2dCanvasFx<OBJECT2D, WORLD2D, OPTIONS> {


}
