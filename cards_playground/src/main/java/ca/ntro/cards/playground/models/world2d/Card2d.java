package ca.ntro.cards.playground.models.world2d;

import ca.ntro.app.views.controls.canvas.World2dGraphicsContextFx;
import ca.ntro.cards.models.world2d.CommonCard2d;
import ca.ntro.cards.models.world2d.CommonObject2d;
import ca.ntro.cards.models.world2d.CommonWorld2d;
import ca.ntro.cards.playground.PlaygroundConstants;
import ca.ntro.core.data_structures.trees.region_tree.AnonymousRegion2d;

public class Card2d extends CommonCard2d {

	@Override
	protected double initialWidth() {
		return PlaygroundConstants.INITIAL_CARD_WIDTH_MILIMETERS;
	}

	@Override
	protected double initialHeight() {
		return PlaygroundConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
	}






}
