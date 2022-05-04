package ca.ntro.cards.playground.models;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.Watchable;
import ca.ntro.cards.frontend.views.data.GameViewData;
import ca.ntro.cards.models.values.Card;

public class PlaygroundModel implements Model, Watchable {
	
	private List<Card> cards = new ArrayList<>();

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public void copyTo(GameViewData gameViewData) {
		gameViewData.clearCards();
		
		for(int i = 0; i < cards.size(); i++) {
			gameViewData.addCard(i, cards.get(i));
		}
	}

}
