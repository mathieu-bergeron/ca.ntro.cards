package ca.ntro.cards.frontend.views;


import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.app.views.controls.canvas.World2dResizableCanvasFx;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import ca.ntro.cards.frontend.events.EvtResizeViewport;
import ca.ntro.cards.frontend.events.MouseEvtOnViewer;
import ca.ntro.cards.models.world2d.CommonWorld2d;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

public abstract class GameView extends ViewFx {
	
	@SuppressWarnings("rawtypes")
	protected abstract World2dResizableCanvasFx viewerCanvas();

	@SuppressWarnings("rawtypes")
	protected abstract World2dCanvasFx tabletopCanvas();

	protected abstract Pane dashboardContainer();

	protected abstract Pane mainContainer();
	
	protected abstract double initialWorldHeight();
	protected abstract double initialWorldWidth();

	protected abstract double initialTabletopScreenHeight();
	
	protected double worldWidth() {
		return viewerCanvas().worldWidth();
	}

	protected double worldHeight() {
		return viewerCanvas().worldHeight();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initializeViewerCanvas();
		initializeTabletopCanvas();
		
		installMouseEvtOnMainDisplay();
		installEvtMoveViewport();
		installEvtResizeViewport();
	
	}

	private void initializeViewerCanvas() {
		viewerCanvas().setFocusTraversable(true);

		Platform.runLater(() -> {
			viewerCanvas().requestFocus();
		});
		
		viewerCanvas().setWorldWidth(initialWorldWidth());
		viewerCanvas().setWorldHeight(initialWorldHeight());
	}

	private void initializeTabletopCanvas() {
		double screenHeight = initialTabletopScreenHeight();
		double screenWidth = screenHeight * initialWorldWidth() / initialWorldHeight();

		tabletopCanvas().setWidth(screenWidth);
		tabletopCanvas().setHeight(screenHeight);

		tabletopCanvas().setWorldWidth(initialWorldWidth());
		tabletopCanvas().setWorldHeight(initialWorldHeight());

		tabletopCanvas().relocateResizeViewport(0, 0, initialWorldWidth(), initialWorldHeight());
	}

	@SuppressWarnings("unchecked")
	private void installMouseEvtOnMainDisplay() {
		MouseEvtOnViewer mouseEvtOnMainDisplay = NtroApp.newEvent(MouseEvtOnViewer.class);
		
		viewerCanvas().addMouseEventFilter(MouseEvent.ANY, (evtFx, worldX, worldY) -> {
			
			mouseEvtOnMainDisplay.setEvtFx(evtFx);
			mouseEvtOnMainDisplay.setWorldX(worldX);
			mouseEvtOnMainDisplay.setWorldY(worldY);
			
			mouseEvtOnMainDisplay.trigger();

		});
	}

	private void installEvtMoveViewport() {
		EvtMoveViewport evtMoveViewport = NtroApp.newEvent(EvtMoveViewport.class);
		
		viewerCanvas().setOnKeyPressed(evtFx -> {
			
			if(evtFx.getCode().equals(KeyCode.W)
					|| evtFx.getCode().equals(KeyCode.UP)) {
				
				evtMoveViewport.setIncrementX(0);
				evtMoveViewport.setIncrementY(-10);
				evtMoveViewport.trigger();

			} else if(evtFx.getCode().equals(KeyCode.S)
					|| evtFx.getCode().equals(KeyCode.DOWN)) {
				
				evtMoveViewport.setIncrementX(0);
				evtMoveViewport.setIncrementY(+10);
				evtMoveViewport.trigger();

			} else if(evtFx.getCode().equals(KeyCode.A)
					|| evtFx.getCode().equals(KeyCode.LEFT)) {
				
				evtMoveViewport.setIncrementX(-10);
				evtMoveViewport.setIncrementY(0);
				evtMoveViewport.trigger();

			} else if(evtFx.getCode().equals(KeyCode.D)
					|| evtFx.getCode().equals(KeyCode.RIGHT)) {
				
				evtMoveViewport.setIncrementX(+10);
				evtMoveViewport.setIncrementY(0);
				evtMoveViewport.trigger();
			}

		});
	}

	private void installEvtResizeViewport() {

		EvtResizeViewport evtResizeViewport = NtroApp.newEvent(EvtResizeViewport.class);

		viewerCanvas().setOnKeyTyped(evtFx -> {
			if(evtFx.getCharacter().equals("+")) {
				
				evtResizeViewport.setFactor(0.9);
				evtResizeViewport.trigger();
				
			}else if(evtFx.getCharacter().equals("-")) {

				evtResizeViewport.setFactor(1.1);
				evtResizeViewport.trigger();
			}
		});
		
		viewerCanvas().addEventFilter(ScrollEvent.ANY, evtFx -> {
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
	public void displayWorld2d(CommonWorld2d world2d) {
		tabletopCanvas().displayWorld2d(world2d);
		viewerCanvas().displayWorld2d(world2d);
	}

	public void clearCanvas() {
		tabletopCanvas().clearCanvas();
		viewerCanvas().clearCanvas();
	}

	public void resizeViewport(double factor) {
		viewerCanvas().resizeViewport(viewerCanvas().viewportWidth() * factor, 
				                      viewerCanvas().viewportHeight() * factor);
		
	}

	public void moveViewport(double incrementX, double incrementY) {
		viewerCanvas().relocateViewport(viewerCanvas().viewportTopLeftX() + incrementX, 
				                        viewerCanvas().viewportTopLeftY() + incrementY);
	}

	@SuppressWarnings("unchecked")
	public void displayViewport() {
		tabletopCanvas().drawOnWorld(gc -> {
			
			gc.strokeRect(viewerCanvas().viewportTopLeftX(),
					      viewerCanvas().viewportTopLeftY(),
					      viewerCanvas().viewportWidth(),
					      viewerCanvas().viewportHeight());
		});
	}

	public void displayDashboardView(DashboardView dashboardView) {
		dashboardContainer().getChildren().clear();
		dashboardContainer().getChildren().add(dashboardView.rootNode());
	}
}
