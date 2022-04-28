package ca.ntro.cards.frontend.views.data;

import ca.ntro.app.frontend.ViewData;
import ca.ntro.cards.frontend.views.TabletopView;
import ca.ntro.cards.frontend.views.utils.FpsCounter;
import ca.ntro.cards.models.world2d.World2dCards;

public class TabletopViewData implements ViewData {

	private World2dCards world2d = new World2dCards();
	private FpsCounter fpsCounter = new FpsCounter();

	public void onTimePasses(double secondsElapsed) {
		world2d.onTimePasses(secondsElapsed);
	}

	public void displayOn(TabletopView tabletopView) {
		fpsCounter.onNewFrame();

		tabletopView.clearCanvas();
		tabletopView.displayViewport();
		tabletopView.displayFps(String.format("FPS %.0f", fpsCounter.currentFps()));
		tabletopView.displayWorld2d(world2d);
	}
}
