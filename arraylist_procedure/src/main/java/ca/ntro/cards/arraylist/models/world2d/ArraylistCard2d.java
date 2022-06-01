package ca.ntro.cards.arraylist.models.world2d;

import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.arraylist.ArraylistConstants;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class ArraylistCard2d extends ProcedureCard2d<ArraylistProcedureObject2d, ArraylistProcedureWorld2d, ArraylistProcedureDrawingOptions> {

	public ArraylistCard2d() {
		super();
	}


	public ArraylistCard2d(AbstractCard card) {
		super(card);
	}

	public ArraylistCard2d(int rank, Suit suit) {
		super(rank, suit);
	}

	@Override
	protected double initialWidth() {
		return ArraylistConstants.INITIAL_CARD_WIDTH_MILIMETERS;
	}

	@Override
	protected double initialHeight() {
		return ArraylistConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
	}

}
