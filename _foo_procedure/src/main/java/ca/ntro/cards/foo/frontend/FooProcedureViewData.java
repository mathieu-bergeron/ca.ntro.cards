package ca.ntro.cards.foo.frontend;

import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.foo.models.world2d.FooCard2d;
import ca.ntro.cards.foo.models.world2d.FooProcedureDrawingOptions;
import ca.ntro.cards.foo.models.world2d.FooProcedureObject2d;
import ca.ntro.cards.foo.models.world2d.FooProcedureWorld2d;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class FooProcedureViewData extends ProcedureViewData<FooProcedureObject2d, FooProcedureWorld2d, FooProcedureDrawingOptions> {

	@Override
	protected FooProcedureWorld2d newWorld2d() {
		return new FooProcedureWorld2d();
	}

	@Override
	protected ProcedureCard2d newCard2d(AbstractCard card) {
		return new FooCard2d(card);
	}

	@Override
	protected FooProcedureDrawingOptions defaultDrawingOptions() {
		return new FooProcedureDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}


}
