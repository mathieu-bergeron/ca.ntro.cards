package ca.ntro.cards.common.frontend.events;

import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.cards.common.frontend.views.CanvasView;

public class EvtResizeViewport extends EventNtro {
	
	private double factor;

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	public void applyTo(CanvasView tabletopView) {
		tabletopView.resizeViewport(factor);
	}

}
