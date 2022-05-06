package ca.ntro.cards.foo.models;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.cards.foo.FooConstants;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.values.Card;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class FooCardsModel extends CardsModel {
	
	private List<Card> cards = new ArrayList<>();


	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	protected void updateViewDataImpl(CardsViewData cardsViewData) {

		double cardWidth = FooConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = FooConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
		
		for(int i = 0; i < cards.size(); i++) {

			double targetTopLeftX = cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight / 2;

			cardsViewData.addOrUpdateCard(cards.get(i),
					                     targetTopLeftX,
					                     targetTopLeftY);
		}
	}

	public void updateCardsInOrder(List<Card> cards) {
		setCards(cards);
		incrementVersion();
	}

	@Override
	public void createFirstVersion() {
		cards.add(new Card(3, Suit.HEARTS));
		cards.add(new Card(6, Suit.CLUBS));
		cards.add(new Card(4, Suit.SPADES));
		cards.add(new Card(10, Suit.DIAMONDS));
		cards.add(new Card(5, Suit.HEARTS));
	}
	
	@Override
	protected Card cardById(String cardId) {
		Card result = null;
		
		for(Card candidate : cards) {
			if(candidate.hasId(cardId)){
				result = candidate;
				break;
			}
		}
		
		return result;
	}

	@Override
	protected Stream<Card> cards() {
		return new StreamNtro<Card>() {
			@Override
			public void forEach_(Visitor<Card> visitor) throws Throwable {
				for(Card card : cards) {
					visitor.visit(card);
				}
			}
		};
	}

	@Override
	protected void addCardImpl(Card card) {
		cards.add(card);
	}

}
