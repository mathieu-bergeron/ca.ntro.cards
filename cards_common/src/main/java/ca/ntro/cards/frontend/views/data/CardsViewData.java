package ca.ntro.cards.frontend.views.data;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ntro.app.frontend.ViewData;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.cards.frontend.views.CardsView;
import ca.ntro.cards.frontend.views.utils.FpsCounter;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.world2d.CommonWorld2d;
import ca.ntro.core.stream.Stream;
import ca.ntro.cards.models.world2d.CommonDrawingOptionsDefault;
import ca.ntro.cards.models.world2d.CommonDrawingOptions;
import javafx.scene.input.MouseEvent;

public abstract class CardsViewData implements ViewData {

	private CommonWorld2d world2d = newWorld2d();
	private FpsCounter fpsCounter = new FpsCounter();
	private CommonDrawingOptions options = new CommonDrawingOptionsDefault();
	
	protected abstract CommonWorld2d newWorld2d();
	
	protected CommonWorld2d world2d() {
		return world2d;
	}
	
	public void setDrawingOptions(CommonDrawingOptions options) {
		this.options = options;
	}

	public void onTimePasses(double secondsElapsed) {
		world2d.onTimePasses(secondsElapsed);
	}

	public void displayOn(CardsView gameView, 
			              DashboardView dashboardView) {
		fpsCounter.onNewFrame();

		gameView.clearCanvas();
		gameView.displayViewport();
		gameView.displayWorld2d(world2d, options);

		dashboardView.displayFps(String.format("FPS %.0f", fpsCounter.currentFps()));
	}

	public void dispatchMouseEvent(World2dMouseEventFx world2dMouseEventFx) {
		world2d.dispatchMouseEvent(world2dMouseEventFx);
	}

	public abstract void addOrUpdateCard(Card card, double topLeftX, double topLeftY);

	public void removeCardsNotIn(Stream<Card> cards) {
		Set<String> cardIds = new HashSet<>();

		cards.forEach(c -> {
			cardIds.add(c.id());
		});

		world2d.removeObject2dNotIn(cardIds);
	}

}
