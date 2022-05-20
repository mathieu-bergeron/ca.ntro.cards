package ca.ntro.cards.common.models.world2d;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;

public class Marker2d extends CommonObject2d {
	
	private String id;
	
	
	public Marker2d(String id) {
		this.id = id;
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	protected boolean onMouseEvent(World2dMouseEventFx mouseEvent) {
		return false;
	}

	@Override
	public void draw(World2dGraphicsContext gc, CommonDrawingOptions options) {
		
		gc.setFill(NtroApp.colorFromString("#03cffc"));
		gc.fillArc(getTopLeftX(), 
				   getTopLeftY(), 
				   getWidth(), 
				   getHeight(), 
				   0, 
				   360);
	}

	@Override
	public void initialize() {
		setWidth(25);
		setHeight(25);
	}

}
