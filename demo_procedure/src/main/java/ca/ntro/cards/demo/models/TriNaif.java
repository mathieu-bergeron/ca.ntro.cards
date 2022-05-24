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
	
	protected List<C> listeSource = new ArrayList<>();
	protected List<C> listeCible = new ArrayList<>();

	@Override
	public void copyDataFrom(TriNaif otherModel) {
		listeSource.clear();
		listeSource.addAll(otherModel.listeSource);
		
		listeCible.clear();
		listeCible.addAll(otherModel.listeCible);
		
		indicePlusPetit = otherModel.indicePlusPetit;
		indiceCandidat = otherModel.indiceCandidat;
		indiceProchainVide = otherModel.indiceProchainVide;

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

	public List<C> getListeSource() {
		return listeSource;
	}

	public void setListeSource(List<C> listeSource) {
		this.listeSource = listeSource;
	}

	public List<C> getListeCible() {
		return listeCible;
	}

	public void setListeCible(List<C> listeCible) {
		this.listeCible = listeCible;
	}

	@Override
	protected void updateViewDataImpl(DemoProcedureViewData cardsViewData) {

		double cardWidth = DemoConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = DemoConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
		
		List<AbstractCard> topCards = new ArrayList<>();
		List<AbstractCard> bottomCards = new ArrayList<>();

		for(int i = 0; i < listeSource.size(); i++) {

			double targetTopLeftX = cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight * 2;
			
			AbstractCard card = (Card) listeSource.get(i);
			
			if(card == null) {
				card = new NullCard();
			}

			cardsViewData.addOrUpdateCard(card,
					                      targetTopLeftX,
					                      targetTopLeftY);

			bottomCards.add(card);
			cardsViewData.displayCardFaceDown(card);
		}

		for(int i = 0; i < listeCible.size(); i++) {

			double targetTopLeftX = cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight / 2;
			
			AbstractCard card = (Card) listeCible.get(i);
			
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
		listeSource.clear();
		for(Card card : sourceList) {
			listeSource.add((C) card);
		}
		
		listeCible.clear();
		for(Card card : targetList) {
			listeCible.add((C) card);
		}

		incrementVersion();
	}

	@Override
	public void initializeAsTestCase(TestCaseDescriptor descriptor) {
		
		if(descriptor.testCaseId().equals("ex01")) {

			listeSource.add((C) new Card(2, Suit.CLUBS));
			listeSource.add((C) new Card(5, Suit.CLUBS));
			listeSource.add((C) new Card(5, Suit.DIAMONDS));
			listeSource.add((C) new Card(5, Suit.HEARTS));
			listeSource.add((C) new Card(7, Suit.SPADES));
			listeSource.add((C) new Card(2, Suit.HEARTS));

		}else {
			
			for(int i = 0; i < descriptor.size(); i++) {

				listeSource.add((C) new Card(2 + Ntro.random().nextInt(8), Suit.random()));

			}
		}

		for(int i = 0; i < listeSource.size(); i++) {
			listeCible.add(null);
		}
	}

	@Override
	public int testCaseSize() {
		return listeSource.size();
	}
	
	@Override
	protected Card cardById(String cardId) {
		Card result = (Card) cardById(cardId, listeSource);

		if(result == null) {
			result = (Card) cardById(cardId, listeCible);
		}

		return result;
	}
	
	private Card cardById(String cardId, List<C> list) {
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
				visitList(visitor, listeSource);
				visitList(visitor, listeCible);
			}

			private void visitList(Visitor<Card> visitor, List<C> listToVisit) throws Throwable {
				for(C card : listToVisit) {
					visitor.visit((Card) card);
				}
			}
		};
	}

	@Override
	protected void addCardImpl(Card card) {
		listeSource.add((C) card);
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
