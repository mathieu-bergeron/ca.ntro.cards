package ca.ntro.cards.demo.frontend.views.data;

import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.models.values.AbstractCard;
import ca.ntro.cards.models.world2d.CommonWorld2d;
import ca.ntro.cards.demo.models.world2d.Card2d;
import ca.ntro.cards.demo.models.world2d.World2d;

public class DemoCardsViewData extends CardsViewData {

	@Override
	protected CommonWorld2d newWorld2d() {
		return new World2d();
	}

	@Override
	public void addOrUpdateCard(AbstractCard card,
			                    double targetTopLeftX,
			                    double targetTopLeftY) {

		Card2d card2d = null;

		if(card != null) {
			card2d = (Card2d) world2d().objectById(card.id());
		}

		if(card2d == null) {

			card2d = new Card2d(card);
			world2d().addObject2d(card2d);

		}else {
			
			card2d.setCard(card);
			
		}
		
		if(card2d.isNullCard()) {
			
			card2d.setTopLeftX(targetTopLeftX);
			card2d.setTopLeftY(targetTopLeftY);
			
		}else {
			
			card2d.setTarget(targetTopLeftX, targetTopLeftY);

		}
		
	}

}
