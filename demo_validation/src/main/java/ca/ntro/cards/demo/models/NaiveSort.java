package ca.ntro.cards.demo.models;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.cards.demo.DemoConstants;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.values.AbstractCard;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.values.NullCard;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public abstract class NaiveSort<C extends Comparable<C>> extends CardsModel<NaiveSort<C>> {
	
	protected int smallest = -1;
	protected int candidate = -1;
	protected int nextEmpty = 0;
	
	protected List<C> source = new ArrayList<>();
	protected List<C> target = new ArrayList<>();

	@Override
	public void copyDataFrom(NaiveSort<C> otherModel) {
		setSourceArray(otherModel.getSourceArray());
		setTargetArray(otherModel.getTargetArray());
		setIndexOfSmallestElement(otherModel.getIndexOfSmallestElement());
		setIndexOfCandidateSmallestElement(otherModel.getIndexOfCandidateSmallestElement());
		setIndexOfNextEmptySpace(otherModel.getIndexOfNextEmptySpace());
	}

	public int getIndexOfSmallestElement() {
		return smallest;
	}

	public void setIndexOfSmallestElement(int indexOfSmallestElement) {
		this.smallest = indexOfSmallestElement;
	}

	public int getIndexOfCandidateSmallestElement() {
		return candidate;
	}

	public void setIndexOfCandidateSmallestElement(int indexOfCandidateSmallestElement) {
		this.candidate = indexOfCandidateSmallestElement;
	}

	public int getIndexOfNextEmptySpace() {
		return nextEmpty;
	}

	public void setIndexOfNextEmptySpace(int indexOfNextEmptySpace) {
		this.nextEmpty = indexOfNextEmptySpace;
	}

	public List<C> getSourceArray() {
		return source;
	}

	public void setSourceArray(List<C> sourceArray) {
		this.source = sourceArray;
	}

	public List<C> getTargetArray() {
		return target;
	}

	public void setTargetArray(List<C> targetArray) {
		this.target = targetArray;
	}

	@Override
	protected void updateViewDataImpl(CardsViewData cardsViewData) {

		double cardWidth = DemoConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = DemoConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
		
		List<AbstractCard> topCards = new ArrayList<>();
		List<AbstractCard> bottomCards = new ArrayList<>();

		for(int i = 0; i < source.size(); i++) {

			double targetTopLeftX = cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight * 2;
			
			AbstractCard card = (Card) source.get(i);
			
			if(card == null) {
				card = new NullCard();
			}

			cardsViewData.addOrUpdateCard(card,
					                      targetTopLeftX,
					                      targetTopLeftY);

			bottomCards.add(card);
			cardsViewData.displayCardFaceDown(card);
		}

		for(int i = 0; i < target.size(); i++) {

			double targetTopLeftX = cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight / 2;
			
			AbstractCard card = (Card) target.get(i);
			
			if(card == null) {
				card = new NullCard();
			}

			cardsViewData.addOrUpdateCard(card,
					                      targetTopLeftX,
					                      targetTopLeftY);
			topCards.add(card);
		}

		double markerTopLeftX = 10 + cardWidth + cardWidth / 2 + getIndexOfSmallestElement() * cardWidth * 3 / 2;
		double markerTopLeftY = cardHeight * 3 + cardHeight / 3;
		
		cardsViewData.addOrUpdateMarker("smallestElement", markerTopLeftX, markerTopLeftY);
		
		if(getIndexOfSmallestElement() >= 0 && getIndexOfSmallestElement() < bottomCards.size()) {
			AbstractCard smallestCard = bottomCards.get(getIndexOfSmallestElement());
			cardsViewData.displayCardFaceUp(smallestCard);
			
		}
		
		if(getIndexOfCandidateSmallestElement() >= 0 && getIndexOfCandidateSmallestElement() < bottomCards.size()) {
			AbstractCard candidateCard = bottomCards.get(getIndexOfCandidateSmallestElement());
			cardsViewData.displayCardFaceUp(candidateCard);
		}

		if(getIndexOfNextEmptySpace() >= 0 && getIndexOfNextEmptySpace() < topCards.size()) {
			for(int i = getIndexOfNextEmptySpace() + 1; i < topCards.size(); i++) {
				AbstractCard futureCard = topCards.get(i);
				cardsViewData.displayCardFaceDown(futureCard);
			}

		}
		
	}

	public void updateCards(List<Card> sourceList, List<Card> targetList) {
		source.clear();
		for(Card card : sourceList) {
			source.add((C) card);
		}
		
		target.clear();
		for(Card card : targetList) {
			target.add((C) card);
		}

		incrementVersion();
	}

	@Override
	public void createFirstVersion() {
		
		for(int i = 0; i < 20; i++) {
			source.add((C) new Card(2 + Ntro.random().nextInt(8), Suit.random()));
		}
		
		/*
		sourceArray.add((C) new Card(2, Suit.CLUBS));
		sourceArray.add((C) new Card(5, Suit.CLUBS));
		sourceArray.add((C) new Card(5, Suit.DIAMONDS));
		sourceArray.add((C) new Card(5, Suit.HEARTS));
		sourceArray.add((C) new Card(7, Suit.SPADES));
		sourceArray.add((C) new Card(2, Suit.HEARTS));
		*/
		
		for(int i = 0; i < source.size(); i++) {
			target.add(null);
		}
	}
	
	@Override
	protected Card cardById(String cardId) {
		Card result = (Card) cardById(cardId, source);

		if(result == null) {
			result = (Card) cardById(cardId, target);
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
				visitList(visitor, source);
				visitList(visitor, target);
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
		source.add((C) card);
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
	
	public abstract void sort();
	
	@Override
	public void run() {
		sort();
	}

}
