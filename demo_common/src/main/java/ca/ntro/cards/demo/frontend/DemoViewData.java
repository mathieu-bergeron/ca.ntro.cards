package ca.ntro.cards.demo.frontend;

import ca.ntro.cards.common.models.values.AbstractCard;
import ca.ntro.cards.demo.models.world2d.DemoDrawingOptions;
import ca.ntro.cards.demo.models.world2d.DemoObject2d;
import ca.ntro.cards.demo.models.world2d.DemoWorld2d;
import ca.ntro.cards.frontend.ProcedureViewData;

public class DemoViewData extends ProcedureViewData<DemoObject2d, DemoWorld2d, DemoDrawingOptions> {

	@Override
	protected DemoWorld2d newWorld2d() {
		return new DemoWorld2d();
	}

	@Override
	public void addOrUpdateCard(AbstractCard card, double topLeftX, double topLeftY) {
	}


}
