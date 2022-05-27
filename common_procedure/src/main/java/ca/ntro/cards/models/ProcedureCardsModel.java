package ca.ntro.cards.models;



import ca.ntro.app.models.Model;
import ca.ntro.app.models.WriteObjectGraph;
import ca.ntro.app.models.Watch;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.identifyers.IdNotUniqueException;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.frontend.views.ProcedureVariablesView;
import ca.ntro.cards.models.world2d.ProcedureDrawingOptions;
import ca.ntro.cards.models.world2d.ProcedureObject2d;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_graph.Initialize;
import ca.ntro.core.stream.Stream;

public abstract class      ProcedureCardsModel<CARDS_MODEL    extends ProcedureCardsModel,
                                               OBJECT2D       extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                               WORLD2D        extends ProcedureWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                               OPTIONS        extends ProcedureDrawingOptions,
                                               VIEW_DATA      extends ProcedureViewData<OBJECT2D, WORLD2D, OPTIONS>,
                                               VARIABLES_VIEW extends ProcedureVariablesView>

                extends    CommonExecutableModel<CARDS_MODEL, OBJECT2D, WORLD2D, OPTIONS, VIEW_DATA>

                implements Model, Watch, Initialize, WriteObjectGraph {

	protected abstract Card cardById(String cardId);
	
	protected abstract Stream<Card> cards();
	
	@Override
	public void initialize() {
	}
	
	public void addCard(Card card) {
		if(cardById(card.id()) != null) {
			Ntro.throwException(new IdNotUniqueException(card.id()));
		}

		addCardImpl(card);
	}
	
	protected abstract void addCardImpl(Card card);


	public void updateViewData(VIEW_DATA viewData) {

		viewData.removeCardsNotIn(cards());
		
		updateViewData(viewData);
	}
	
	public abstract void displayOn(VARIABLES_VIEW variablesView);
	
	

}
