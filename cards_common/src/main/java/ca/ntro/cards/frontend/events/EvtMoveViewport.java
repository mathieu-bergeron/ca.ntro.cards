package ca.ntro.cards.frontend.events;

import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.cards.frontend.views.GameView;

public class EvtMoveViewport extends EventNtro {
	
	private double incrementX;
	private double incrementY;

	public double getIncrementX() {
		return incrementX;
	}

	public void setIncrementX(double incrementX) {
		this.incrementX = incrementX;
	}

	public double getIncrementY() {
		return incrementY;
	}

	public void setIncrementY(double incrementY) {
		this.incrementY = incrementY;
	}
	
	public void applyTo(GameView tabletopView) {
		tabletopView.moveViewport(incrementX, incrementY);
	}

}
