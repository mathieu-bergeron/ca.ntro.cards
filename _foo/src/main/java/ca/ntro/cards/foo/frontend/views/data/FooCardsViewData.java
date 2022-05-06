package ca.ntro.cards.foo.frontend.views.data;

import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.world2d.CommonWorld2d;
import ca.ntro.cards.foo.models.world2d.Card2d;
import ca.ntro.cards.foo.models.world2d.World2d;

public class FooCardsViewData extends CardsViewData {

	@Override
	protected CommonWorld2d newWorld2d() {
		return new World2d();
	}

	@Override
	public void addOrUpdateCard(Card card,
			                    double targetTopLeftX,
			                    double targetTopLeftY) {

		Card2d card2d = (Card2d) world2d().objectById(card.id());

		if(card2d == null) {
			card2d = new Card2d(card);
			world2d().addObject2d(card2d);
		}
		
		card2d.setTarget(targetTopLeftX, targetTopLeftY);

		card2d.setCard(card);
	}

}
