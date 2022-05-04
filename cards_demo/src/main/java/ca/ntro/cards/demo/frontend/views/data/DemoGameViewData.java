package ca.ntro.cards.demo.frontend.views.data;

import ca.ntro.cards.frontend.views.data.GameViewData;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.world2d.CommonWorld2d;
import ca.ntro.cards.demo.models.world2d.Card2d;
import ca.ntro.cards.demo.models.world2d.World2d;

public class DemoGameViewData extends GameViewData {

	@Override
	protected CommonWorld2d newWorld2d() {
		return new World2d();
	}

	@Override
	public void addOrUpdateCard(int index, Card card) {
		Card2d card2d = (Card2d) world2d().objectById(card.id());

		if(card2d == null) {
			card2d = new Card2d(card);
			world2d().addObject2d(card2d);
		}
		
		card2d.setTargetTopLeftX(25 + index * 75);
		card2d.setTargetTopLeftY(50);

		card2d.setCard(card);
	}

}
