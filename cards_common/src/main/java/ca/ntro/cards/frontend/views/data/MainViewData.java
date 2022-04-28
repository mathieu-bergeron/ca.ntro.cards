package ca.ntro.cards.frontend.views.data;

import ca.ntro.app.frontend.ViewData;
import ca.ntro.cards.frontend.views.MainView;
import ca.ntro.cards.frontend.views.utils.FpsCounter;
import ca.ntro.cards.models.world2d.World2dCards;
import javafx.scene.input.MouseEvent;

public class MainViewData implements ViewData {

	private World2dCards world2d = new World2dCards();
	private FpsCounter fpsCounter = new FpsCounter();

	public void onTimePasses(double secondsElapsed) {
		world2d.onTimePasses(secondsElapsed);
	}

	public void displayOn(MainView tabletopView) {
		fpsCounter.onNewFrame();

		tabletopView.clearCanvas();
		tabletopView.displayViewport();
		tabletopView.displayFps(String.format("FPS %.0f", fpsCounter.currentFps()));
		tabletopView.displayWorld2d(world2d);
	}

	public void dispatchMouseEvent(MouseEvent evtFx, double worldX, double worldY) {
		world2d.dispatchMouseEvent(evtFx, worldX, worldY);
	}
}
