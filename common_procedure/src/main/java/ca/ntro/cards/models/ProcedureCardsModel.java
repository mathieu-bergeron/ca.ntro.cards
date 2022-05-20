package ca.ntro.cards.models;


import ca.ntro.app.models.Model;
import ca.ntro.app.models.WriteObjectGraph;
import ca.ntro.app.models.Watch;
import ca.ntro.cards.common.models.CommonCardsModel;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.models.world2d.ProcedureDrawingOptions;
import ca.ntro.cards.models.world2d.ProcedureObject2d;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;
import ca.ntro.core.reflection.object_graph.Initialize;

public abstract class      ProcedureCardsModel<CARDS_MODEL extends ProcedureCardsModel,
                                               OBJECT2D    extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                               WORLD2D     extends ProcedureWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                               OPTIONS     extends ProcedureDrawingOptions,
                                               VIEW_DATA   extends ProcedureViewData<OBJECT2D, WORLD2D, OPTIONS>>

                extends    CommonCardsModel<CARDS_MODEL, OBJECT2D, WORLD2D, OPTIONS, VIEW_DATA>

                implements Model, Watch, Initialize, WriteObjectGraph {
	
	

		

}
