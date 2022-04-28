package ca.ntro.cards.frontend.views;


import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.app.views.controls.canvas.World2dResizableCanvasFx;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import ca.ntro.cards.frontend.events.EvtResizeViewport;
import ca.ntro.cards.frontend.events.MouseEvtOnMainDisplay;
import ca.ntro.cards.models.world2d.World2dCards;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public abstract class MainView extends ViewFx {
	
	@SuppressWarnings("rawtypes")
	protected abstract World2dResizableCanvasFx getViewerCanvas();

	@SuppressWarnings("rawtypes")
	protected abstract World2dCanvasFx getTabletopCanvas();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Platform.runLater(() -> {
			getViewerCanvas().requestFocus();
		});
		
		getTabletopCanvas().setHeight(100);
		getTabletopCanvas().setWidth(100);
		
		installMouseEvtOnMainDisplay();
		installEvtMoveViewport();
		installEvtResizeViewport();
	}

	@SuppressWarnings("unchecked")
	private void installMouseEvtOnMainDisplay() {
		MouseEvtOnMainDisplay mouseEvtOnMainDisplay = NtroApp.newEvent(MouseEvtOnMainDisplay.class);
		
		getViewerCanvas().addMouseEventFilter(MouseEvent.ANY, (evtFx, worldX, worldY) -> {
			
			mouseEvtOnMainDisplay.setEvtFx(evtFx);
			mouseEvtOnMainDisplay.setWorldX(worldX);
			mouseEvtOnMainDisplay.setWorldY(worldY);
			
			mouseEvtOnMainDisplay.trigger();

		});
	}

	private void installEvtMoveViewport() {
		EvtMoveViewport evtMoveViewport = NtroApp.newEvent(EvtMoveViewport.class);
		
		getViewerCanvas().setOnKeyPressed(evtFx -> {
			
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

	private void installEvtResizeViewport() {

		EvtResizeViewport evtResizeViewport = NtroApp.newEvent(EvtResizeViewport.class);

		getViewerCanvas().setOnKeyTyped(evtFx -> {
			if(evtFx.getCharacter().equals("+")) {
				
				evtResizeViewport.setFactor(0.9);
				evtResizeViewport.trigger();
				
			}else if(evtFx.getCharacter().equals("-")) {

				evtResizeViewport.setFactor(1.1);
				evtResizeViewport.trigger();
			}
		});
		
		getViewerCanvas().addEventFilter(ScrollEvent.ANY, evtFx -> {
			if(evtFx.getDeltaY() > 0) {

				evtResizeViewport.setFactor(0.9);
				evtResizeViewport.trigger();
				
			}else if(evtFx.getDeltaY() < 0) {

				evtResizeViewport.setFactor(1.1);
				evtResizeViewport.trigger();

			}
		});
	}

	@SuppressWarnings("unchecked")
	public void displayWorld2d(World2dCards world2d) {
		getViewerCanvas().displayWorld2d(world2d);
	}

	public void clearCanvas() {
		getViewerCanvas().clearCanvas();
	}

	public void displayFps(String fps) {
		getViewerCanvas().displayFps(fps);
	}

	public void resizeViewport(double factor) {
		getViewerCanvas().resizeViewport(getViewerCanvas().viewportWidth() * factor, 
				                   getViewerCanvas().viewportHeight() * factor);
	}

	public void moveViewport(double incrementX, double incrementY) {
		getViewerCanvas().relocateViewport(getViewerCanvas().viewportTopLeftX() + incrementX, 
				                     getViewerCanvas().viewportTopLeftY() + incrementY);
	}

	public void displayViewport() {
		getViewerCanvas().displayViewport();
	}


}
