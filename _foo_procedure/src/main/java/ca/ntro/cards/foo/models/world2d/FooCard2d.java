package ca.ntro.cards.foo.models.world2d;

import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.foo.FooConstants;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class FooCard2d extends ProcedureCard2d<FooProcedureObject2d, FooProcedureWorld2d, FooProcedureDrawingOptions> {

	public FooCard2d() {
		super();
	}


	public FooCard2d(AbstractCard card) {
		super(card);
	}

	public FooCard2d(int rank, Suit suit) {
		super(rank, suit);
	}

	@Override
	protected double initialWidth() {
		return FooConstants.INITIAL_CARD_WIDTH_MILIMETERS;
	}

	@Override
	protected double initialHeight() {
		return FooConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
	}



	@Override
	protected void flipCard() {
		super.flipCard();
		
		getWorld().registerFlippedCard(this);
	}


}
