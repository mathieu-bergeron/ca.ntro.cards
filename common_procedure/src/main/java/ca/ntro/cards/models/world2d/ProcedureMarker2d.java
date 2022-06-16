package ca.ntro.cards.models.world2d;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.app.frontend.views.elements.Color;

public class   ProcedureMarker2d<OBJECT2D extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                 WORLD2D  extends ProcedureWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                 OPTIONS  extends ProcedureDrawingOptions>

       extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS> {
	
	private String id;
	private String color;
	
	private transient Color ntroColor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		this.ntroColor = NtroApp.colorFromString(color);
	}

	public ProcedureMarker2d(String id) {
		setId(id);
		setColor("#03cffc");
	}

	public ProcedureMarker2d(String id, String color) {
		setId(id);
		setColor(color);
	}

	@Override
	public String id() {
		return getId();
	}

	@Override
	public void draw(World2dGraphicsContext gc, OPTIONS options) {

		gc.setFill(ntroColor);
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
