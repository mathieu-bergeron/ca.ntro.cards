package ca.ntro.cards.freesort.models.world2d;

import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.freesort.FreesortConstants;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class FreesortCard2d extends ProcedureCard2d<FreesortProcedureWorld2d, FreesortProcedureDrawingOptions> {

	public FreesortCard2d() {
		super();
	}


	public FreesortCard2d(AbstractCard card) {
		super(card);
	}

	public FreesortCard2d(int rank, Suit suit) {
		super(rank, suit);
	}

	@Override
	protected double initialWidth() {
		return FreesortConstants.INITIAL_CARD_WIDTH_MILIMETERS;
	}

	@Override
	protected double initialHeight() {
		return FreesortConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
	}

}
