package ca.ntro.cards.demo.frontend.views.data;

import ca.ntro.cards.frontend.views.data.GameViewData;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.world2d.CommonWorld2d;
import ca.ntro.cards.demo.DemoConstants;
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
		
		double cardWidth = DemoConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = DemoConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
		
		double targetTopLeftX = cardWidth / 2 + index * cardWidth * 3 / 2;
		double targetTopLeftY = cardHeight / 2;
		
		card2d.setTarget(targetTopLeftX, targetTopLeftY);

		card2d.setCard(card);
	}

}
