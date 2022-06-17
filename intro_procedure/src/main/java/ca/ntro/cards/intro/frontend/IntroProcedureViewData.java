package ca.ntro.cards.intro.frontend;

import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.intro.models.world2d.IntroCard2d;
import ca.ntro.cards.intro.models.world2d.IntroProcedureDrawingOptions;
import ca.ntro.cards.intro.models.world2d.IntroProcedureWorld2d;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class IntroProcedureViewData extends ProcedureViewData<IntroProcedureWorld2d, IntroProcedureDrawingOptions> {

	@Override
	protected IntroProcedureWorld2d newWorld2d() {
		return new IntroProcedureWorld2d();
	}

	@Override
	protected ProcedureCard2d newCard2d(AbstractCard card) {
		return new IntroCard2d(card);
	}

	@Override
	protected IntroProcedureDrawingOptions defaultDrawingOptions() {
		return new IntroProcedureDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}


}
