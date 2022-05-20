package ca.ntro.cards.common.frontend.views;


import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.app.views.controls.canvas.World2dResizableCanvasFx;
import ca.ntro.cards.common.frontend.events.EvtMoveViewport;
import ca.ntro.cards.common.frontend.events.EvtResizeViewport;
import ca.ntro.cards.common.frontend.events.MouseEvtOnTabletop;
import ca.ntro.cards.common.frontend.events.MouseEvtOnViewer;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.cards.common.models.world2d.CommonWorld2d;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

public abstract class CanvasView extends ViewFx {
	
	@SuppressWarnings("rawtypes")
	protected abstract World2dResizableCanvasFx mainCanvas();

	@SuppressWarnings("rawtypes")
	protected abstract World2dCanvasFx previewCanvas();

	protected abstract Pane dashboardContainer();

	protected abstract Pane cardsViewContainer();
	
	protected abstract double initialWorldHeight();
	protected abstract double initialWorldWidth();

	protected abstract double initialTabletopScreenHeight();
	
	protected double worldWidth() {
		return mainCanvas().worldWidth();
	}

	protected double worldHeight() {
		return mainCanvas().worldHeight();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initializeViewerCanvas();
		initializeTabletopCanvas();
		
		installMouseEvtOnViewer();
		installEvtMoveViewport();
		installEvtResizeViewport();
	
	}

	private void initializeViewerCanvas() {
		mainCanvas().setFocusTraversable(true);

		Platform.runLater(() -> {
			mainCanvas().requestFocus();
		});
		
		mainCanvas().setWorldWidth(initialWorldWidth());
		mainCanvas().setWorldHeight(initialWorldHeight());
	}

	@SuppressWarnings("unchecked")
	private void initializeTabletopCanvas() {
		double screenHeight = initialTabletopScreenHeight();
		double screenWidth = screenHeight * initialWorldWidth() / initialWorldHeight();

		previewCanvas().setWidth(screenWidth);
		previewCanvas().setHeight(screenHeight);

		previewCanvas().setWorldWidth(initialWorldWidth());
		previewCanvas().setWorldHeight(initialWorldHeight());

		previewCanvas().relocateResizeViewport(0, 0, initialWorldWidth(), initialWorldHeight());

		MouseEvtOnTabletop mouseEvtOnTabletop = NtroApp.newEvent(MouseEvtOnTabletop.class);
		

		previewCanvas().addMouseEventFilter(MouseEvent.ANY, world2dMouseEventFx -> {
			
			mouseEvtOnTabletop.setWorld2dMouseEventFx(world2dMouseEventFx);
			mouseEvtOnTabletop.trigger();

		});
		
	}

	@SuppressWarnings("unchecked")
	private void installMouseEvtOnViewer() {
		MouseEvtOnViewer mouseEvtOnViewer = NtroApp.newEvent(MouseEvtOnViewer.class);
		
		mainCanvas().addMouseEventFilter(MouseEvent.ANY, world2dMouseEventFx -> {
			
			mouseEvtOnViewer.setWorld2dMouseEventFx(world2dMouseEventFx);
			mouseEvtOnViewer.trigger();

		});
	}

	private void installEvtMoveViewport() {
		EvtMoveViewport evtMoveViewport = NtroApp.newEvent(EvtMoveViewport.class);
		
		mainCanvas().setOnKeyPressed(evtFx -> {
			
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

		mainCanvas().setOnKeyTyped(evtFx -> {
			if(evtFx.getCharacter().equals("+")) {
				
				evtResizeViewport.setFactor(0.9);
				evtResizeViewport.trigger();
				
			}else if(evtFx.getCharacter().equals("-")) {

				evtResizeViewport.setFactor(1.1);
				evtResizeViewport.trigger();
			}
		});
		
		mainCanvas().addEventFilter(ScrollEvent.ANY, evtFx -> {
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
	public void displayWorld2d(CommonWorld2d world2d, CommonDrawingOptions options) {
		previewCanvas().displayWorld2d(world2d, options);
		mainCanvas().displayWorld2d(world2d, options);
	}

	public void clearCanvas() {
		previewCanvas().clearCanvas();
		mainCanvas().clearCanvas();
	}

	public void resizeViewport(double factor) {
		double oldWidth = mainCanvas().viewportWidth();
		double oldHeight = mainCanvas().viewportHeight();

		double newWidth = oldWidth * factor;
		double newHeight = oldHeight * factor;
		
		double newTopLeftX = mainCanvas().viewportTopLeftX() - (newWidth - oldWidth) / 2;
		double newTopLeftY = mainCanvas().viewportTopLeftY() - (newHeight - oldHeight) / 2;
		
		mainCanvas().relocateResizeViewport(newTopLeftX,
				                              newTopLeftY, 
				                              newWidth, 
				                              newHeight);
		
	}

	public void moveViewport(double incrementX, double incrementY) {
		mainCanvas().relocateViewport(mainCanvas().viewportTopLeftX() + incrementX, 
				                        mainCanvas().viewportTopLeftY() + incrementY);
	}

	@SuppressWarnings("unchecked")
	public void displayViewport() {
		previewCanvas().drawOnWorld(gc -> {

			gc.strokeRect(mainCanvas().viewportTopLeftX(),
					      mainCanvas().viewportTopLeftY(),
					      mainCanvas().viewportWidth(),
					      mainCanvas().viewportHeight());
		});
	}

	public void displayDashboardView(DashboardView dashboardView) {
		dashboardContainer().getChildren().clear();
		dashboardContainer().getChildren().add(dashboardView.rootNode());
	}

	public void mouseEvtOnTabletop(World2dMouseEventFx world2dMouseEventFx) {
		MouseEvent evtFx = world2dMouseEventFx.rawMouseEvent();
		double worldX = world2dMouseEventFx.worldX();
		double worldY = world2dMouseEventFx.worldY();
		
		if(evtFx.getEventType().equals(MouseEvent.MOUSE_CLICKED)
				|| evtFx.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
			
			mainCanvas().relocateViewport(worldX - mainCanvas().viewportWidth() / 2, 
					                        worldY - mainCanvas().viewportHeight() / 2);
		}
	}
}
