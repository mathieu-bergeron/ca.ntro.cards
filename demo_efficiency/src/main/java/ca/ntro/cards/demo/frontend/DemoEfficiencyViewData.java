package ca.ntro.cards.demo.frontend;

import ca.ntro.cards.demo.models.world2d.DemoEfficiencyDrawingOptions;
import ca.ntro.cards.demo.models.world2d.DemoEfficiencyObject2d;
import ca.ntro.cards.demo.models.world2d.DemoEfficiencyWorld2d;
import ca.ntro.cards.efficiency.frontend.EfficiencyViewData;

public class   DemoEfficiencyViewData 

       extends EfficiencyViewData<DemoEfficiencyObject2d, 
                                  DemoEfficiencyWorld2d,
                                  DemoEfficiencyDrawingOptions> {

	@Override
	protected DemoEfficiencyWorld2d newWorld2d() {
		return new DemoEfficiencyWorld2d();
	}

	@Override
	protected DemoEfficiencyDrawingOptions defaultDrawingOptions() {
		return new DemoEfficiencyDrawingOptions() {

		};
	}

}
