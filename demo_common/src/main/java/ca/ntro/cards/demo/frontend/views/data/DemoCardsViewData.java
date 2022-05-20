package ca.ntro.cards.demo.frontend.views.data;

import ca.ntro.cards.common.models.values.AbstractCard;
import ca.ntro.cards.common.models.world2d.CommonWorld2d;
import ca.ntro.cards.demo.models.world2d.DemoCard2d;
import ca.ntro.cards.demo.models.world2d.DemoWorld2d;
import ca.ntro.cards.frontend.ProcedureViewData;

public class DemoCardsViewData extends ProcedureViewData {

	@Override
	protected CommonWorld2d newWorld2d() {
		return new DemoWorld2d();
	}

	@Override
	public void addOrUpdateCard(AbstractCard card,
			                    double targetTopLeftX,
			                    double targetTopLeftY) {

		DemoCard2d card2d = null;

		if(card != null) {
			card2d = (DemoCard2d) world2d().objectById(card.id());
		}

		if(card2d == null) {

			card2d = new DemoCard2d(card);
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
