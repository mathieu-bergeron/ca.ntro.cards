package ca.ntro.cards.demo.models;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.models.values.cards.NullCard;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.demo.DemoConstants;
import ca.ntro.cards.demo.frontend.DemoProcedureViewData;
import ca.ntro.cards.demo.models.world2d.DemoProcedureDrawingOptions;
import ca.ntro.cards.demo.models.world2d.DemoProcedureObject2d;
import ca.ntro.cards.demo.models.world2d.DemoProcedureWorld2d;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class   TriNaif<C extends Comparable<C>> 

       extends ProcedureCardsModel<TriNaif, DemoProcedureObject2d, DemoProcedureWorld2d, DemoProcedureDrawingOptions, DemoProcedureViewData> { 
                	
                	
	protected int indicePlusPetit = -1;
	protected int indiceCandidat = -1;
	protected int indiceProchainVide = 0;
	
	protected C[] source = (C[]) new Card[0];
	protected C[] cible = (C[]) new Card[0];

	@Override
	public void copyDataFrom(TriNaif other) {
		int size = other.source.length;

		source = (C[]) new Card[size];
		cible = (C[]) new Card[size];

		for(int i = 0; i < size; i++) {
			source[i] = (C) other.source[i];
		}

		for(int i = 0; i < size; i++) {
			cible[i] = (C) other.cible[i];
		}
		
		indicePlusPetit = other.indicePlusPetit;
		indiceCandidat = other.indiceCandidat;
		indiceProchainVide = other.indiceProchainVide;
	}

	public int getIndicePlusPetit() {
		return indicePlusPetit;
	}

	public void setIndicePlusPetit(int indicePlusPetit) {
		this.indicePlusPetit = indicePlusPetit;
	}

	public int getIndiceCandidat() {
		return indiceCandidat;
	}

	public void setIndiceCandidat(int indiceCandidat) {
		this.indiceCandidat = indiceCandidat;
	}

	public int getIndiceProchainVide() {
		return indiceProchainVide;
	}

	public void setIndiceProchainVide(int indiceProchainVide) {
		this.indiceProchainVide = indiceProchainVide;
	}

	public C[] getSource() {
		return source;
	}

	public void setSource(C[] source) {
		this.source = source;
	}

	public C[] getCible() {
		return cible;
	}

	public void setCible(C[] cible) {
		this.cible = cible;
	}

	@Override
	protected void updateViewDataImpl(DemoProcedureViewData cardsViewData) {

		double cardWidth = DemoConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = DemoConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
		
		List<AbstractCard> topCards = new ArrayList<>();
		List<AbstractCard> bottomCards = new ArrayList<>();

		for(int i = 0; i < source.length; i++) {

			double targetTopLeftX = cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight * 2;
			
			AbstractCard card = (Card) source[i];
			
			if(card == null) {
				card = new NullCard();
			}
			
			cardsViewData.addOrUpdateCard(card,
					                      targetTopLeftX,
					                      targetTopLeftY);

			bottomCards.add(card);
			//cardsViewData.displayCardFaceDown(card);
		}

		for(int i = 0; i < cible.length; i++) {

			double targetTopLeftX = cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight / 2;
			
			AbstractCard card = (Card) cible[i];
			
			if(card == null) {
				card = new NullCard();
			}

			cardsViewData.addOrUpdateCard(card,
					                      targetTopLeftX,
					                      targetTopLeftY);
			topCards.add(card);
		}

		double markerTopLeftX = 10 + cardWidth + cardWidth / 2 + getIndicePlusPetit() * cardWidth * 3 / 2;
		double markerTopLeftY = cardHeight * 3 + cardHeight / 3;
		
		cardsViewData.addOrUpdateMarker("smallestElement", markerTopLeftX, markerTopLeftY);
		
		if(getIndicePlusPetit() >= 0 && getIndicePlusPetit() < bottomCards.size()) {
			AbstractCard smallestCard = bottomCards.get(getIndicePlusPetit());
			cardsViewData.displayCardFaceUp(smallestCard);
			
		}
		
		if(getIndiceCandidat() >= 0 && getIndiceCandidat() < bottomCards.size()) {
			AbstractCard candidateCard = bottomCards.get(getIndiceCandidat());
			cardsViewData.displayCardFaceUp(candidateCard);
		}

		if(getIndiceProchainVide() >= 0 && getIndiceProchainVide() < topCards.size()) {
			for(int i = getIndiceProchainVide() + 1; i < topCards.size(); i++) {
				AbstractCard futureCard = topCards.get(i);
				cardsViewData.displayCardFaceDown(futureCard);
			}

		}
		
	}

	public void updateCards(List<Card> sourceList, List<Card> targetList) {
		int size = sourceList.size();

		source = (C[]) new Object[size];
		cible = (C[]) new Object[size];

		for(int i = 0; i < size; i++) {
			source[i] = (C) sourceList.get(i);
		}

		for(int i = 0; i < size; i++) {
			cible[i] = (C) targetList.get(i);
		}

		incrementVersion();
	}

	@Override
	public void initializeAsTestCase(TestCaseDescriptor descriptor) {
		
		if(descriptor.testCaseId().equals("ex01")) {
			
			source = (C[]) new Card[] {new Card(2, Suit.CLUBS), 
					                   new Card(5, Suit.CLUBS), 
					                   new Card(5, Suit.DIAMONDS), 
					                   new Card(5, Suit.HEARTS), 
					                   new Card(7, Suit.SPADES), 
					                   new Card(2, Suit.HEARTS)};

		}else {
			
			source = (C[]) new Card[descriptor.size()];
			
			for(int i = 0; i < descriptor.size(); i++) {
				
				source[i] = (C) new Card(2 + Ntro.random().nextInt(8), Suit.random());
			}
		}
		
		cible = (C[]) new Card[source.length];

		for(int i = 0; i < source.length; i++) {
			cible[i] = null;
		}
	}

	@Override
	public int testCaseSize() {
		return source.length;
	}
	
	@Override
	protected Card cardById(String cardId) {
		Card result = (Card) cardById(cardId, source);

		if(result == null) {
			result = (Card) cardById(cardId, cible);
		}

		return result;
	}
	
	private Card cardById(String cardId, C[] list) {
		Card result = null;
		
		for(C candidate : list) {
			if(candidate instanceof Card) {
				Card candidateCard = (Card) candidate;
				if(candidateCard.hasId(cardId)) {
					result = candidateCard;
				}
			}
		}
		
		return result;
	}

	@Override
	protected Stream<Card> cards() {
		return new StreamNtro<Card>() {
			@Override
			public void forEach_(Visitor<Card> visitor) throws Throwable {
				visitList(visitor, source);
				visitList(visitor, cible);
			}

			private void visitList(Visitor<Card> visitor, C[] listToVisit) throws Throwable {
				for(C card : listToVisit) {
					visitor.visit((Card) card);
				}
			}
		};
	}

	@Override
	protected void addCardImpl(Card card) {
		Card[] newSource = new Card[source.length+1];
		
		for(int i = 0; i < source.length; i++) {
			newSource[i] = (Card) source[i];
		}

		newSource[source.length] = card;
		
		source = (C[]) newSource;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		format(builder);
		
		return builder.toString();

	}
	
	public void format(StringBuilder builder) {
		cards().forEach(card -> {
			builder.append(System.lineSeparator());
			card.format(builder);
		});
	}
	

	@Override
	public void onBeforeRunning() {
		indicePlusPetit = 0;
		indiceCandidat = 0;
		indiceProchainVide = 0;
	}
	
	@Override
	public void run() {
		trier();
	}

	@Override
	public void onAfterRunning() {
	}

	public void trier() {
	}


}
