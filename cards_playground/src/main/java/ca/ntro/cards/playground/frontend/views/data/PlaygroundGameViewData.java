package ca.ntro.cards.playground.frontend.views.data;

import ca.ntro.cards.frontend.views.data.GameViewData;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.world2d.CommonWorld2d;
import ca.ntro.cards.playground.models.world2d.Card2d;
import ca.ntro.cards.playground.models.world2d.World2d;

public class PlaygroundGameViewData extends GameViewData {

	@Override
	protected CommonWorld2d newWorld2d() {
		return new World2d();
	}

	@Override
	public void addOrUpdateCard(int index, Card card) {
		Card2d card2d = new Card2d(card);
		
		card2d.setTopLeftX(10 + index * 60);
		card2d.setTopLeftY(50);
		
		world2d().addObject2d(card2d);

	}

}
