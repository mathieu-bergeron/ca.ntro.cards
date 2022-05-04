package ca.ntro.cards.playground.models.world2d;

import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.world2d.CommonCard2d;
import ca.ntro.cards.playground.PlaygroundConstants;

public class Card2d extends CommonCard2d {

	public Card2d(int rank, Suit suit) {
		super(rank, suit);
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
