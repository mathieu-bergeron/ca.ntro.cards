package ca.ntro.cards.common.frontend;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.app.frontend.ViewData;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.cards.common.frontend.utils.FpsCounter;
import ca.ntro.cards.common.frontend.views.CommonCanvasView;
import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import ca.ntro.cards.common.models.values.AbstractCard;
import ca.ntro.cards.common.models.values.Card;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptionsDefault;
import ca.ntro.cards.common.models.world2d.CommonObject2d;
import ca.ntro.cards.common.models.world2d.CommonWorld2d;
import ca.ntro.core.stream.Stream;

public abstract class CommonViewData<OBJECT2D extends CommonObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                     WORLD2D  extends CommonWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                     OPTIONS  extends CommonDrawingOptions>

       implements     ViewData {
	
	protected WORLD2D              world2d = newWorld2d();
	private   FpsCounter           fpsCounter = new FpsCounter();
	private   CommonDrawingOptions options = new CommonDrawingOptionsDefault();
	
	protected abstract WORLD2D newWorld2d();

	protected WORLD2D world2d() {
		return world2d;
	}
	
	public void setDrawingOptions(CommonDrawingOptions options) {
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

	public abstract void addOrUpdateCard(AbstractCard card, double topLeftX, double topLeftY);

	public void removeCardsNotIn(Stream<Card> cards) {
		Set<String> cardIds = new HashSet<>();

		cards.forEach(c -> {
			if(c != null) {
				cardIds.add(c.id());
			}
		});

		world2d.removeObject2dNotIn(cardIds);
	}

}
