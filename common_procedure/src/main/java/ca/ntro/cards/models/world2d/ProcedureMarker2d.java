package ca.ntro.cards.models.world2d;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;

public class   ProcedureMarker2d<OBJECT2D extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                 WORLD2D  extends ProcedureWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                 OPTIONS  extends ProcedureDrawingOptions>

       extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS> {
	
	private String id;
	
	public ProcedureMarker2d(String id) {
		this.id = id;
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public void draw(World2dGraphicsContext gc, OPTIONS options) {
		
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
