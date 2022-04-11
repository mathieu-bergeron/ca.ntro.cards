package ca.ntro.app.frontend.views.controls.canvas;

public class CanvasNtro<GC     extends GraphicsContextNtro<GC,CANVAS>,
                        CANVAS extends CanvasNtro<GC,CANVAS>> 

       implements Canvas<GC> {
	
	private double width;
	private double height;
	private GC graphicsContext;

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public GC getGraphicsContext() {
		return graphicsContext;
	}

	public void setGraphicsContext(GC graphicsContext) {
		this.graphicsContext = graphicsContext;
	}

	@Override
	public double width() {
		return getWidth();
	}

	@Override
	public double height() {
		return getHeight();
	}

	@Override
	public GC graphicsContext() {
		return getGraphicsContext();
	}
}
