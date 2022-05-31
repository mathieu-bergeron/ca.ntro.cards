package ca.ntro.cards.models;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.WriteObjectGraph;
import ca.ntro.app.models.Watch;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.enums.Suit;
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

	public void updateViewData(VIEW_DATA viewData) {

		viewData.removeCardsNotIn(cards());
		
		updateViewData(viewData);
	}
	
	public abstract void displayOn(VARIABLES_VIEW variablesView);

	protected Card[] randomArrayOfUniqueCards(int size) {
		List<Card> randomList = randomListOfUniqueCards(size);

		Card[] array = new Card[size];
		
		for(int i = 0; i < size; i++) {
			array[i] = randomList.get(i);
		}
		
		return array;
	}

	protected List<Card> randomListOfUniqueCards(int size) {
		List<Card> orderedList = orderedListOfRandomCards(size);

		List<Card> randomList = new ArrayList<>();
		
		while(!orderedList.isEmpty()) {
			int choosenCardIndex = Ntro.random().nextInt(orderedList.size());
			Card choosenCard = orderedList.get(choosenCardIndex);
			
			randomList.add(choosenCard);
			orderedList.remove(choosenCardIndex);
		}
		
		return randomList;
	}

	protected List<Card> orderedListOfRandomCards(int size) {
		List<Card> orderedList = new ArrayList<>();
		
		Map<Suit, Integer> numberOfCardsBySuit = new HashMap<>();
		int sum = 0;
		for(Suit suit : Suit.values()) {
			int numberOfCardsThisSuit = size / Suit.values().length;
			numberOfCardsBySuit.put(suit, numberOfCardsThisSuit);
			sum += numberOfCardsThisSuit;
		}
		
		while(sum < size) {
			int numberOfCardsOfHearts = numberOfCardsBySuit.get(Suit.HEARTS);
			sum++;
			numberOfCardsOfHearts++;
			numberOfCardsBySuit.put(Suit.HEARTS, numberOfCardsOfHearts);
		}

		for(Suit suit : Suit.values()) {
			for(int i = 0; i < numberOfCardsBySuit.get(suit); i++) {
				orderedList.add(new Card(i+1, suit));
			}
		}

		return orderedList;
	}
	

}
