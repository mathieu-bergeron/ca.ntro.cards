package ca.ntro.app.frontend.views.controls.canvas;

public interface Canvas<GC extends GraphicsContext> {
	
	double width();
	double height();

	GC graphicsContext();

}
