package ca.ntro.cards.freesort.frontend;

import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.freesort.models.world2d.FreesortCard2d;
import ca.ntro.cards.freesort.models.world2d.FreesortProcedureDrawingOptions;
import ca.ntro.cards.freesort.models.world2d.FreesortProcedureObject2d;
import ca.ntro.cards.freesort.models.world2d.FreesortProcedureWorld2d;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class FreesortProcedureViewData extends ProcedureViewData<FreesortProcedureObject2d, FreesortProcedureWorld2d, FreesortProcedureDrawingOptions> {

	@Override
	protected FreesortProcedureWorld2d newWorld2d() {
		return new FreesortProcedureWorld2d();
	}

	@Override
	protected ProcedureCard2d newCard2d(AbstractCard card) {
		return new FreesortCard2d(card);
	}

	@Override
	protected FreesortProcedureDrawingOptions defaultDrawingOptions() {
		return new FreesortProcedureDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}


}
