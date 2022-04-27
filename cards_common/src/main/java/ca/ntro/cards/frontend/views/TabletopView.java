package ca.ntro.cards.frontend.views;


import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import ca.ntro.cards.models.world2d.World2dCards;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

public abstract class TabletopView extends ViewFx {
	
	@SuppressWarnings("rawtypes")
	protected abstract World2dCanvasFx getCanvas();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Platform.runLater(() -> {
			getCanvas().requestFocus();
		});
		
		
		/*
		getMainContainer().addEventFilter(MouseEvent.ANY, evtFx -> {
			//System.out.println("MouseEvent: " + evtFx);
		});*/
		
		
		//getMainContainer().setFocusTraversable(true);
		//getCanvas().setFocusTraversable(true);
		
		//getMainContainer().requestFocus();

		installEvtMoveCanvas();

	}

	private void installEvtMoveCanvas() {
		EvtMoveViewport evtMoveViewport = NtroApp.newEvent(EvtMoveViewport.class);
		
		getCanvas().setOnKeyPressed(evtFx -> {
			
			if(evtFx.getCode().equals(KeyCode.W)
					|| evtFx.getCode().equals(KeyCode.UP)) {
				
				evtMoveViewport.setIncrementX(0);
				evtMoveViewport.setIncrementY(+10);
				evtMoveViewport.trigger();

			} else if(evtFx.getCode().equals(KeyCode.S)
					|| evtFx.getCode().equals(KeyCode.DOWN)) {
				
				evtMoveViewport.setIncrementX(0);
				evtMoveViewport.setIncrementY(-10);
				evtMoveViewport.trigger();

			} else if(evtFx.getCode().equals(KeyCode.A)
					|| evtFx.getCode().equals(KeyCode.LEFT)) {
				
				evtMoveViewport.setIncrementX(+10);
				evtMoveViewport.setIncrementY(0);
				evtMoveViewport.trigger();

			} else if(evtFx.getCode().equals(KeyCode.D)
					|| evtFx.getCode().equals(KeyCode.RIGHT)) {
				
				evtMoveViewport.setIncrementX(-10);
				evtMoveViewport.setIncrementY(0);
				evtMoveViewport.trigger();
			}

		});
	}

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

	public void resizeViewport(double factor) {
		getCanvas().resizeViewport(getCanvas().viewportWidth() * factor, 
				                   getCanvas().viewportHeight() * factor);
	}

	public void moveViewport(double incrementX, double incrementY) {
		getCanvas().relocateViewport(getCanvas().viewportTopLeftX() + incrementX, 
				                     getCanvas().viewportTopLeftY() + incrementY);
	}

	public void displayViewport() {
		getCanvas().displayViewport();
	}


}
