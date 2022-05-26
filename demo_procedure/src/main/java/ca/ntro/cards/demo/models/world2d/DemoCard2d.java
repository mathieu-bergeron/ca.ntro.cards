package ca.ntro.cards.demo.models.world2d;

import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.demo.DemoConstants;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class DemoCard2d extends ProcedureCard2d<DemoProcedureObject2d, DemoProcedureWorld2d, DemoProcedureDrawingOptions> {



	public DemoCard2d() {
		super();
	}

	public DemoCard2d(AbstractCard card) {
		super(card);
	}

	public DemoCard2d(int rank, Suit suit) {
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
