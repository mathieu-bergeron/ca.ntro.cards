package ca.ntro.cards.frontend.views.controls;

import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.cards.models.world2d.ProcedureDrawingOptions;
import ca.ntro.cards.models.world2d.ProcedureObject2d;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

public class   PreviewCanvas<OBJECT2D extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                             WORLD2D  extends ProcedureWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                             OPTIONS  extends ProcedureDrawingOptions>

       extends World2dCanvasFx<OBJECT2D, WORLD2D, OPTIONS> {

}
