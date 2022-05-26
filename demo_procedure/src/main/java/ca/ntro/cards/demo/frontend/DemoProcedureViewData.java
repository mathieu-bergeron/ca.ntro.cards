package ca.ntro.cards.demo.frontend;

import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.demo.models.world2d.DemoCard2d;
import ca.ntro.cards.demo.models.world2d.DemoProcedureDrawingOptions;
import ca.ntro.cards.demo.models.world2d.DemoProcedureObject2d;
import ca.ntro.cards.demo.models.world2d.DemoProcedureWorld2d;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class DemoProcedureViewData extends ProcedureViewData<DemoProcedureObject2d, DemoProcedureWorld2d, DemoProcedureDrawingOptions> {

	@Override
	protected DemoProcedureWorld2d newWorld2d() {
		return new DemoProcedureWorld2d();
	}

	@Override
	protected ProcedureCard2d newCard2d(AbstractCard card) {
		return new DemoCard2d(card);
	}

	@Override
	protected DemoProcedureDrawingOptions defaultDrawingOptions() {
		return new DemoProcedureDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}


}
