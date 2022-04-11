package ca.ntro.app.frontend.views.controls.canvas;

public abstract class GraphicsContextNtro<GC     extends GraphicsContextNtro<GC,CANVAS>, 
                                          CANVAS extends CanvasNtro<GC,CANVAS>> 

       implements GraphicsContext<GC,CANVAS> {
	
	private CANVAS canvas;

	public CANVAS getCanvas() {
		return canvas;
	}

	public void setCanvas(CANVAS canvas) {
		this.canvas = canvas;
	}

}
