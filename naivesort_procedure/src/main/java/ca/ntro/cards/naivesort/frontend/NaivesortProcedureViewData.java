package ca.ntro.cards.naivesort.frontend;

import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.naivesort.models.world2d.NaivesortCard2d;
import ca.ntro.cards.naivesort.models.world2d.NaivesortProcedureDrawingOptions;
import ca.ntro.cards.naivesort.models.world2d.NaivesortProcedureWorld2d;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class NaivesortProcedureViewData extends ProcedureViewData<NaivesortProcedureWorld2d, NaivesortProcedureDrawingOptions> {

	@Override
	protected NaivesortProcedureWorld2d newWorld2d() {
		return new NaivesortProcedureWorld2d();
	}

	@Override
	protected ProcedureCard2d newCard2d(AbstractCard card) {
		return new NaivesortCard2d(card);
	}

	@Override
	protected NaivesortProcedureDrawingOptions defaultDrawingOptions() {
		return new NaivesortProcedureDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}


}
