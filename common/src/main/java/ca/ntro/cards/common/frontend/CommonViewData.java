package ca.ntro.cards.common.frontend;


import ca.ntro.app.frontend.ViewData;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.cards.common.frontend.utils.FpsCounter;
import ca.ntro.cards.common.frontend.views.CommonCanvasView;
import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.cards.common.models.world2d.CommonObject2d;
import ca.ntro.cards.common.models.world2d.CommonWorld2d;

public abstract class CommonViewData<OBJECT2D extends CommonObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                     WORLD2D  extends CommonWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                     OPTIONS  extends CommonDrawingOptions>

       implements     ViewData {
	
	protected WORLD2D              world2d = newWorld2d();
	private   FpsCounter           fpsCounter = new FpsCounter();
	private   OPTIONS options = defaultDrawingOptions();
	
	protected abstract WORLD2D newWorld2d();

	protected abstract OPTIONS defaultDrawingOptions();

	protected WORLD2D world2d() {
		return world2d;
	}
	
	public void setDrawingOptions(OPTIONS options) {
		this.options = options;
	}

	public void onTimePasses(double secondsElapsed) {

		world2d.onTimePasses(secondsElapsed);
	}

	public void displayOn(CommonCanvasView canvasView, 
			              CommonDashboardView dashboardView) {

		fpsCounter.onNewFrame();

		canvasView.clearCanvas();
		canvasView.displayViewport();
		canvasView.displayWorld2d(world2d, options);

		dashboardView.displayFps(String.format("FPS %.0f", fpsCounter.currentFps()));
	}

	public void dispatchMouseEvent(World2dMouseEventFx world2dMouseEventFx) {
		world2d.dispatchMouseEvent(world2dMouseEventFx);
	}


}
