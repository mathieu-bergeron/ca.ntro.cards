package ca.ntro.cards.frontend.views.data;

import ca.ntro.app.frontend.ViewData;
import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.cards.frontend.views.GameView;
import ca.ntro.cards.frontend.views.utils.FpsCounter;
import ca.ntro.cards.models.world2d.CommonWorld2d;
import javafx.scene.input.MouseEvent;

public abstract class GameViewData implements ViewData {

	private CommonWorld2d world2d = newWorld2d();
	private FpsCounter fpsCounter = new FpsCounter();
	
	protected abstract CommonWorld2d newWorld2d();
	

	public void onTimePasses(double secondsElapsed) {
		world2d.onTimePasses(secondsElapsed);
	}

	public void displayOn(GameView mainView, DashboardView dashboardView) {
		fpsCounter.onNewFrame();

		mainView.clearCanvas();
		mainView.displayViewport();
		mainView.displayWorld2d(world2d);

		dashboardView.displayFps(String.format("FPS %.0f", fpsCounter.currentFps()));
	}

	public void dispatchMouseEvent(MouseEvent evtFx, double worldX, double worldY) {
		world2d.dispatchMouseEvent(evtFx, worldX, worldY);
	}

}
