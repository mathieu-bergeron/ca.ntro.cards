package ca.ntro.cards.intro.models.world2d;

import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.intro.IntroConstants;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class IntroCard2d extends ProcedureCard2d<IntroProcedureWorld2d, IntroProcedureDrawingOptions> {

	public IntroCard2d() {
		super();
	}


	public IntroCard2d(AbstractCard card) {
		super(card);
	}

	public IntroCard2d(int rank, Suit suit) {
		super(rank, suit);
	}

	@Override
	protected double initialWidth() {
		return IntroConstants.INITIAL_CARD_WIDTH_MILIMETERS;
	}

	@Override
	protected double initialHeight() {
		return IntroConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
	}

}
