package ca.ntro.cards.common.models;


import ca.ntro.app.models.Model;
import ca.ntro.app.models.Watch;
import ca.ntro.app.models.WriteObjectGraph;
import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.cards.common.models.world2d.CommonObject2d;
import ca.ntro.cards.common.models.world2d.CommonWorld2d;
import ca.ntro.core.reflection.object_graph.Initialize;

public abstract class CommonCanvasModel<CANVAS_MODEL extends CommonCanvasModel, 
                                        OBJECT2D     extends CommonObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                        WORLD2D      extends CommonWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                        OPTIONS      extends CommonDrawingOptions,
                                        VIEW_DATA    extends CommonViewData<OBJECT2D, WORLD2D, OPTIONS>>

       implements     Model, Initialize {

	public abstract void updateViewData(VIEW_DATA cardsViewData);
	
	public abstract void copyDataFrom(CANVAS_MODEL otherModel);


}
