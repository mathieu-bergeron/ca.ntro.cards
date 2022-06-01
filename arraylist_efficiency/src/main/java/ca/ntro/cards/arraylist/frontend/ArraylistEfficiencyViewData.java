package ca.ntro.cards.arraylist.frontend;

import ca.ntro.cards.arraylist.models.world2d.ArraylistEfficiencyDrawingOptions;
import ca.ntro.cards.arraylist.models.world2d.ArraylistEfficiencyObject2d;
import ca.ntro.cards.arraylist.models.world2d.ArraylistEfficiencyWorld2d;
import ca.ntro.cards.efficiency.frontend.EfficiencyViewData;

public class   ArraylistEfficiencyViewData 

       extends EfficiencyViewData<ArraylistEfficiencyObject2d, 
                                  ArraylistEfficiencyWorld2d,
                                  ArraylistEfficiencyDrawingOptions> {

	@Override
	protected ArraylistEfficiencyWorld2d newWorld2d() {
		return new ArraylistEfficiencyWorld2d();
	}

	@Override
	protected ArraylistEfficiencyDrawingOptions defaultDrawingOptions() {
		return new ArraylistEfficiencyDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}

}
