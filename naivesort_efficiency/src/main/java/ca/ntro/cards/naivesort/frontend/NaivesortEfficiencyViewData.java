package ca.ntro.cards.naivesort.frontend;

import ca.ntro.cards.naivesort.models.world2d.NaivesortEfficiencyDrawingOptions;
import ca.ntro.cards.naivesort.models.world2d.NaivesortEfficiencyObject2d;
import ca.ntro.cards.naivesort.models.world2d.NaivesortEfficiencyWorld2d;
import ca.ntro.cards.efficiency.frontend.EfficiencyViewData;

public class   NaivesortEfficiencyViewData 

       extends EfficiencyViewData<NaivesortEfficiencyObject2d, 
                                  NaivesortEfficiencyWorld2d,
                                  NaivesortEfficiencyDrawingOptions> {

	@Override
	protected NaivesortEfficiencyWorld2d newWorld2d() {
		return new NaivesortEfficiencyWorld2d();
	}

	@Override
	protected NaivesortEfficiencyDrawingOptions defaultDrawingOptions() {
		return new NaivesortEfficiencyDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}

}
