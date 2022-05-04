package ca.ntro.cards.playground.models.world2d;

import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.world2d.CommonCard2d;
import ca.ntro.cards.playground.PlaygroundConstants;

public class Card2d extends CommonCard2d {

	public Card2d(Card card) {
		super(card);
	}

	public Card2d(long id, int rank, Suit suit) {
		super(id, rank, suit);
	}

	@Override
	protected double initialWidth() {
		return PlaygroundConstants.INITIAL_CARD_WIDTH_MILIMETERS;
	}

	@Override
	protected double initialHeight() {
		return PlaygroundConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
	}

}
