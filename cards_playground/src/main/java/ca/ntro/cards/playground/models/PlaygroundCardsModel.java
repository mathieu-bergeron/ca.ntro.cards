package ca.ntro.cards.playground.models;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.cards.CommonConstants;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.values.Card;
import ca.ntro.core.initialization.Ntro;

public class PlaygroundCardsModel extends CardsModel {
	
	private Map<String, Card> cards = new HashMap<>();

	public Map<String, Card> getCards() {
		return cards;
	}

	public void setCards(Map<String, Card> cards) {
		this.cards = cards;
	}

	@Override
	protected Card cardById(String cardId) {
		return cards.get(cardId);
	}

	@Override
	public void createFirstVersion() {
		
		Card card01 = new Card(1,2, Suit.HEARTS);
		Card card02 = new Card(2, 4, Suit.CLUBS);
		Card card03 = new Card(3, 6, Suit.DIAMONDS);
		Card card04 = new Card(4, 10, Suit.SPADES);
		
		cards.put(card01.id(), card01);
		cards.put(card02.id(), card02);
		cards.put(card03.id(), card03);
		cards.put(card04.id(), card04);
	}

	@Override
	public void updateViewData(CardsViewData gameViewData) {
		for(Card card : cards.values()) {
			
			double targetTopLeftX = CommonConstants.INITIAL_CARD_WIDTH_MILIMETERS / 3 + Ntro.random().nextDouble(CommonConstants.INITIAL_CARD_WIDTH_MILIMETERS * 5);
			double targetTopLeftY = CommonConstants.INITIAL_CARD_HEIGHT_MILIMETERS / 3 + Ntro.random().nextDouble(CommonConstants.INITIAL_CARD_HEIGHT_MILIMETERS * 5);
			
			gameViewData.addOrUpdateCard(card, targetTopLeftX, targetTopLeftY);
		}
	}

}
