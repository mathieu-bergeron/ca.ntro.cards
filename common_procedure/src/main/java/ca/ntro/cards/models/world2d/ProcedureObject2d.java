package ca.ntro.cards.models.world2d;

import ca.ntro.cards.common.models.world2d.CommonObject2d;

public abstract class ProcedureObject2d<OBJECT2D extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                        WORLD2D  extends ProcedureWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                        OPTIONS  extends ProcedureDrawingOptions>

       extends        CommonObject2d<OBJECT2D, WORLD2D, OPTIONS> {

}
