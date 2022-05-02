package ca.ntro.cards.playground.frontend.views.data;

import ca.ntro.cards.frontend.views.data.GameViewData;
import ca.ntro.cards.models.world2d.CommonWorld2d;
import ca.ntro.cards.playground.models.world2d.World2d;

public class PlaygroundGameViewData extends GameViewData {

	@Override
	protected CommonWorld2d newWorld2d() {
		return new World2d();
	}

}
