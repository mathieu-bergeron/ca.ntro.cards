package ca.ntro.cards.arraylist.frontend;

import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.arraylist.models.world2d.ArraylistCard2d;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureDrawingOptions;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureWorld2d;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class ArraylistProcedureViewData extends ProcedureViewData<ArraylistProcedureWorld2d, ArraylistProcedureDrawingOptions> {

	@Override
	protected ArraylistProcedureWorld2d newWorld2d() {
		return new ArraylistProcedureWorld2d();
	}

	@Override
	protected ProcedureCard2d newCard2d(AbstractCard card) {
		return new ArraylistCard2d(card);
	}

	@Override
	protected ArraylistProcedureDrawingOptions defaultDrawingOptions() {
		return new ArraylistProcedureDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}


}
