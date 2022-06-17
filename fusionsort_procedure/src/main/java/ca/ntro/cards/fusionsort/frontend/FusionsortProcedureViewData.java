package ca.ntro.cards.fusionsort.frontend;

import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortCard2d;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortProcedureDrawingOptions;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortProcedureWorld2d;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.models.world2d.ProcedureCard2d;

public class FusionsortProcedureViewData extends ProcedureViewData<FusionsortProcedureWorld2d, FusionsortProcedureDrawingOptions> {

	@Override
	protected FusionsortProcedureWorld2d newWorld2d() {
		return new FusionsortProcedureWorld2d();
	}

	@Override
	protected ProcedureCard2d newCard2d(AbstractCard card) {
		return new FusionsortCard2d(card);
	}

	@Override
	protected FusionsortProcedureDrawingOptions defaultDrawingOptions() {
		return new FusionsortProcedureDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}


}
