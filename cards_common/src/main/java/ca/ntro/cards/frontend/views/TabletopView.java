package ca.ntro.cards.frontend.views;


import ca.ntro.app.frontend.views.controls.canvas.World2dCanvas;
import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.models.world2d.World2dCards;

public abstract class TabletopView extends ViewFx {
	
	@SuppressWarnings("rawtypes")
	protected abstract World2dCanvas getCanvas();

	@SuppressWarnings("unchecked")
	public void displayWorld2d(World2dCards world2d) {
		getCanvas().displayWorld2d(world2d);
	}

	public void clearCanvas() {
		getCanvas().clearCanvas();
	}

	public void displayFps(String fps) {
		getCanvas().displayFps(fps);
	}

	public void resizeViewport(double incrementX, double incrementY) {
		getCanvas().resizeViewport(getCanvas().viewportWidth() + incrementX, 
				                   getCanvas().viewportHeight() + incrementY);
	}

	public void moveViewport(double incrementX, double incrementY) {
		getCanvas().relocateViewport(getCanvas().viewportTopLeftX() + incrementX, 
				                     getCanvas().viewportTopLeftY() + incrementY);
	}

	public void displayViewport() {
		getCanvas().displayViewport();
	}


}
