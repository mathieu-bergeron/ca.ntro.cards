package ca.ntro.cards.freesort.frontend;

import ca.ntro.cards.freesort.models.world2d.FreesortEfficiencyDrawingOptions;
import ca.ntro.cards.freesort.models.world2d.FreesortEfficiencyObject2d;
import ca.ntro.cards.freesort.models.world2d.FreesortEfficiencyWorld2d;
import ca.ntro.cards.efficiency.frontend.EfficiencyViewData;

public class   FreesortEfficiencyViewData 

       extends EfficiencyViewData<FreesortEfficiencyObject2d, 
                                  FreesortEfficiencyWorld2d,
                                  FreesortEfficiencyDrawingOptions> {

	@Override
	protected FreesortEfficiencyWorld2d newWorld2d() {
		return new FreesortEfficiencyWorld2d();
	}

	@Override
	protected FreesortEfficiencyDrawingOptions defaultDrawingOptions() {
		return new FreesortEfficiencyDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}

}
