package ca.ntro.cards.foo.frontend;

import ca.ntro.cards.foo.models.world2d.FooEfficiencyDrawingOptions;
import ca.ntro.cards.foo.models.world2d.FooEfficiencyObject2d;
import ca.ntro.cards.foo.models.world2d.FooEfficiencyWorld2d;
import ca.ntro.cards.efficiency.frontend.EfficiencyViewData;

public class   FooEfficiencyViewData 

       extends EfficiencyViewData<FooEfficiencyObject2d, 
                                  FooEfficiencyWorld2d,
                                  FooEfficiencyDrawingOptions> {

	@Override
	protected FooEfficiencyWorld2d newWorld2d() {
		return new FooEfficiencyWorld2d();
	}

	@Override
	protected FooEfficiencyDrawingOptions defaultDrawingOptions() {
		return new FooEfficiencyDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}

}
