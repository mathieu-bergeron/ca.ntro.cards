package ca.ntro.cards.fusionsort.models.world2d;

import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.fusionsort.FusionsortConstants;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class FusionsortCard2d extends ProcedureCard2d<FusionsortProcedureObject2d, FusionsortProcedureWorld2d, FusionsortProcedureDrawingOptions> {

	public FusionsortCard2d() {
		super();
	}


	public FusionsortCard2d(AbstractCard card) {
		super(card);
	}

	public FusionsortCard2d(int rank, Suit suit) {
		super(rank, suit);
	}

	@Override
	protected double initialWidth() {
		return FusionsortConstants.INITIAL_CARD_WIDTH_MILIMETERS;
	}

	@Override
	protected double initialHeight() {
		return FusionsortConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
	}

}
