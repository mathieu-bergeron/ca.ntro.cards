package ca.ntro.app.frontend.views.controls.canvas;

public interface GraphicsContext<GC extends GraphicsContext<GC, CANVAS>,
                                 CANVAS extends Canvas<GC>> {

	CANVAS getCanvas();

	void save();
	void restore();

	void translate(double x, double y);
	void scale(double x, double y);
	void rotate(double degrees);
	
	

}
