package ca.ntro.cards.demo.frontend;

import ca.ntro.cards.common.models.values.AbstractCard;
import ca.ntro.cards.demo.models.world2d.DemoCard2d;
import ca.ntro.cards.demo.models.world2d.DemoDrawingOptions;
import ca.ntro.cards.demo.models.world2d.DemoObject2d;
import ca.ntro.cards.demo.models.world2d.DemoWorld2d;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class DemoViewData extends ProcedureViewData<DemoObject2d, DemoWorld2d, DemoDrawingOptions> {

	@Override
	protected DemoWorld2d newWorld2d() {
		return new DemoWorld2d();
	}

	@Override
	protected ProcedureCard2d newCard2d(AbstractCard card) {
		return new DemoCard2d();
	}


}
