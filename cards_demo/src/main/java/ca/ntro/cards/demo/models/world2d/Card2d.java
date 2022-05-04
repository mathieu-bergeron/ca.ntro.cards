package ca.ntro.cards.demo.models.world2d;

import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.world2d.CommonCard2d;
import ca.ntro.cards.demo.DemoConstants;

public class Card2d extends CommonCard2d {

	public Card2d(Card card) {
		super(card);
	}

	public Card2d(int rank, Suit suit) {
		super(rank, suit);
	}

	@Override
	protected double initialWidth() {
		return DemoConstants.INITIAL_CARD_WIDTH_MILIMETERS;
	}

	@Override
	protected double initialHeight() {
		return DemoConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
	}

}
