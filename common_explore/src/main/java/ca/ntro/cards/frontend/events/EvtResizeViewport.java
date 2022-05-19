package ca.ntro.cards.frontend.events;

import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.cards.frontend.views.CardsView;

public class EvtResizeViewport extends EventNtro {
	
	private double factor;

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	public void applyTo(CardsView tabletopView) {
		tabletopView.resizeViewport(factor);
	}

}
