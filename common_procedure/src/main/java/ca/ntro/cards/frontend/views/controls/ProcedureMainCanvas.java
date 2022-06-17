package ca.ntro.cards.frontend.views.controls;

import ca.ntro.cards.common.frontend.views.controls.CommonMainCanvas;
import ca.ntro.cards.models.world2d.ProcedureDrawingOptions;
import ca.ntro.cards.models.world2d.ProcedureObject2d;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

public class   ProcedureMainCanvas<WORLD2D  extends ProcedureWorld2d<WORLD2D, OPTIONS>,
                                   OPTIONS  extends ProcedureDrawingOptions>

       extends CommonMainCanvas<ProcedureObject2d<WORLD2D, OPTIONS>, WORLD2D, OPTIONS> {

}
