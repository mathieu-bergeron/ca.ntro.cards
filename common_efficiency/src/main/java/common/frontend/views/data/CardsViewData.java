package common.frontend.views.data;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.ViewData;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.core.stream.Stream;
import common.frontend.views.CardsView;
import common.frontend.views.DashboardView;
import common.frontend.views.utils.FpsCounter;
import common.models.values.AbstractCard;
import common.models.values.Card;
import common.models.world2d.CommonCard2d;
import common.models.world2d.CommonDrawingOptions;
import common.models.world2d.CommonDrawingOptionsDefault;
import common.models.world2d.CommonWorld2d;
import common.models.world2d.Marker2d;

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

	public abstract void addOrUpdateCard(AbstractCard card, double topLeftX, double topLeftY);

	public void addOrUpdateMarker(String markerId, double topLeftX, double topLeftY) {
		
		Marker2d marker2d = null;

		marker2d = (Marker2d) world2d().objectById(markerId);

		if(marker2d == null) {
			marker2d = new Marker2d(markerId);
			world2d().addObject2d(marker2d);
		}
		
		marker2d.setTopLeftX(topLeftX);
		marker2d.setTopLeftY(topLeftY);
		
	}

	public void displayCardFaceDown(AbstractCard card) {
		setCardFaceUp(card, false);
	}

	public void setCardFaceUp(AbstractCard card, boolean faceUp) {

		CommonCard2d card2d = (CommonCard2d) world2d().objectById(card.id());

		if(card2d != null) {
			card2d.getCard().setFaceUp(faceUp);
		}
	}

	public void displayCardFaceUp(AbstractCard card) {
		setCardFaceUp(card, true);
	}


	public void removeCardsNotIn(Stream<Card> cards) {
		Set<String> cardIds = new HashSet<>();

		cards.forEach(c -> {
			if(c != null) {
				cardIds.add(c.id());
			}
		});

		world2d.removeObject2dNotIn(cardIds);

	}

	public void removeNullCards() {
		Set<String> toRemove = new HashSet<>();
		
		for(Object2d object2d : world2d.getObjects()) {
			if(object2d instanceof CommonCard2d) {
				if(((CommonCard2d) object2d).isNullCard()) {
					toRemove.add(object2d.id());
				}
			}
		}
		
		world2d.removeObject2dIn(toRemove);

	}


}
