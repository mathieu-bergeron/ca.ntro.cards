package ca.ntro.cards.common.frontend.events;

import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.cards.common.frontend.views.CanvasView;

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
	
	public void applyTo(CanvasView tabletopView) {
		tabletopView.moveViewport(incrementX, incrementY);
	}

}
