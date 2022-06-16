package ca.ntro.cards.freesort.models;


import ca.ntro.app.NtroApp;
import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.models.values.cards.NullCard;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.freesort.FreesortConstants;
import ca.ntro.cards.freesort.frontend.FreesortProcedureViewData;
import ca.ntro.cards.freesort.frontend.views.FreesortVariablesView;
import ca.ntro.cards.freesort.models.world2d.FreesortProcedureDrawingOptions;
import ca.ntro.cards.freesort.models.world2d.FreesortProcedureObject2d;
import ca.ntro.cards.freesort.models.world2d.FreesortProcedureWorld2d;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class   TriLibre<C extends Comparable<C>> 

       extends ProcedureCardsModel<TriLibre, 
                                   FreesortProcedureObject2d, 
                                   FreesortProcedureWorld2d, 
                                   FreesortProcedureDrawingOptions, 
                                   FreesortProcedureViewData,
                                   FreesortVariablesView> { 
	
	protected Card[] cartes = new Card[0];
	protected boolean trie = false;
	
	public boolean getTrie() {
		return trie;
	}

	public void setTrie(boolean trie) {
		this.trie = trie;
	}

	public Card[] getCartes() {
		return cartes;
	}

	public void setCartes(Card[] cartes) {
		this.cartes = cartes;
	}

	@Override
	public void copyDataFrom(TriLibre other) {
		cartes = new Card[other.cartes.length];
		
		for(int i = 0; i < cartes.length; i++) {
			cartes[i] = other.cartes[i];
		}
		
		trie = other.trie;
	}

	@Override
	public boolean acceptManualModel(TriLibre manualModel) {
		boolean accepted = false;
		
		if(cartes.length != manualModel.cartes.length) {

			accepted = true;

		}else {
			for(int i = 0; i < cartes.length; i++) {
				if(!cartes[i].equals(manualModel.cartes[i])) {
					accepted = true;
					break;
				}
			}
		}

		return accepted;
	}

	@Override
	protected void updateViewDataImpl(FreesortProcedureViewData cardsViewData) {

		double cardWidth = FreesortConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = FreesortConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

		for(int i = 0; i < cartes.length; i++) {

			double targetTopLeftX = cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight * 2;
			
			AbstractCard card = (Card) cartes[i];
			
			if(card == null) {
				card = new NullCard();
			}
			
			cardsViewData.addOrUpdateCard(card,
					                      targetTopLeftX,
					                      targetTopLeftY);

			cardsViewData.displayCardFaceUp(card);

		}
		
		trie = areCardsSorted(cartes); // AJOUTER
	}

	@Override
	public void initializeAsTestCase(AbstractTestCaseDescriptor descriptor) {
		if(descriptor.testCaseId().equals("ex01")) {
			
			cartes = new Card[] {
					new Card(4, Suit.CLUBS),
					new Card(2, Suit.DIAMONDS),
					new Card(4, Suit.SPADES),
					new Card(2, Suit.HEARTS),
					new Card(5, Suit.DIAMONDS),
			};

		} else if(descriptor.testCaseId().contentEquals("ex02")) {

			cartes = new Card[] {
					new Card(9, Suit.CLUBS),
					new Card(8, Suit.CLUBS),
					new Card(5, Suit.HEARTS),
					new Card(3, Suit.SPADES),
					new Card(5, Suit.SPADES),
			};
			
		} else if(descriptor.testCaseId().contentEquals("ex03")) {

			cartes = new Card[] {
					new Card(3, Suit.DIAMONDS)
			};
			
		} else if(descriptor.testCaseId().contentEquals("ex04")) {

			cartes = new Card[] {
					new Card(9, Suit.CLUBS),
					new Card(3, Suit.SPADES),
					new Card(10, Suit.DIAMONDS),
					new Card(3, Suit.CLUBS),
					new Card(9, Suit.HEARTS),
					new Card(1, Suit.SPADES),
					new Card(8, Suit.CLUBS),
					new Card(5, Suit.HEARTS),
					new Card(4, Suit.HEARTS),
					new Card(7, Suit.SPADES),
					new Card(2, Suit.DIAMONDS),
			};
			
		}

	}

	@Override
	public int testCaseSize() {
		return cartes.length;
	}
	
	@Override
	protected Card cardById(String cardId) {
		Card card = null;
		
		for(int i = 0; i < cartes.length; i++) {
			if(cartes[i].hasId(cardId)) {
				card = cartes[i];
				break;
			}
		}
		
		return card;
	}
	
	@Override
	protected Stream<Card> cards() {
		return new StreamNtro<Card>() {
			@Override
			public void forEach_(Visitor<Card> visitor) throws Throwable {
				for(Card card : cartes) {
					visitor.visit(card);
				}
			}
		};
	}

	@Override
	public void run() {
		triLibre();
	}

	public void triLibre() {
	}

	@Override
	public void displayOn(FreesortVariablesView variablesView) {
		int indexOfFirstHearts = 0;
		for(int i = 0; i < cartes.length; i++) {
			if(cartes[i].hasSuit(Suit.HEARTS)) {
				indexOfFirstHearts = i;
				break;
			}
			
		}

		variablesView.displayFooVar01(String.valueOf(indexOfFirstHearts));
		variablesView.displayTrie(String.valueOf(trie)); // AJOUTER
	}
	
	public boolean areCardsSorted(Card[] cards) { // AJOUTER
		boolean sorted = false;
		
		if (cards.length != 0) {
			sorted = true;
			if (cards.length > 1) {
				for (int i = 1; i < cards.length; i++) {		
					if(cards[i-1].compareTo(cards[i]) > 0) {
						 sorted = false;
						 break;
					}
				}
			}
		}
		
		return sorted;
	}

}
