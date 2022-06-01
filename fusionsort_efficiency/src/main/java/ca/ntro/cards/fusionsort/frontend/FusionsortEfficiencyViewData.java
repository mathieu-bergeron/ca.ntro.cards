package ca.ntro.cards.fusionsort.frontend;

import ca.ntro.cards.fusionsort.models.world2d.FusionsortEfficiencyDrawingOptions;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortEfficiencyObject2d;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortEfficiencyWorld2d;
import ca.ntro.cards.efficiency.frontend.EfficiencyViewData;

public class   FusionsortEfficiencyViewData 

       extends EfficiencyViewData<FusionsortEfficiencyObject2d, 
                                  FusionsortEfficiencyWorld2d,
                                  FusionsortEfficiencyDrawingOptions> {

	@Override
	protected FusionsortEfficiencyWorld2d newWorld2d() {
		return new FusionsortEfficiencyWorld2d();
	}

	@Override
	protected FusionsortEfficiencyDrawingOptions defaultDrawingOptions() {
		return new FusionsortEfficiencyDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}

}
