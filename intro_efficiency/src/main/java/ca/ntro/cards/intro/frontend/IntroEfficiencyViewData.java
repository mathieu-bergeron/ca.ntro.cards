package ca.ntro.cards.intro.frontend;

import ca.ntro.cards.intro.models.world2d.IntroEfficiencyDrawingOptions;
import ca.ntro.cards.intro.models.world2d.IntroEfficiencyObject2d;
import ca.ntro.cards.intro.models.world2d.IntroEfficiencyWorld2d;
import ca.ntro.cards.efficiency.frontend.EfficiencyViewData;

public class   IntroEfficiencyViewData 

       extends EfficiencyViewData<IntroEfficiencyObject2d, 
                                  IntroEfficiencyWorld2d,
                                  IntroEfficiencyDrawingOptions> {

	@Override
	protected IntroEfficiencyWorld2d newWorld2d() {
		return new IntroEfficiencyWorld2d();
	}

	@Override
	protected IntroEfficiencyDrawingOptions defaultDrawingOptions() {
		return new IntroEfficiencyDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}

}
