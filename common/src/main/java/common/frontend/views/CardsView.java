package common.frontend.views;


import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.app.views.controls.canvas.World2dResizableCanvasFx;
import common.frontend.events.EvtMoveViewport;
import common.frontend.events.EvtResizeViewport;
import common.frontend.events.MouseEvtOnTabletop;
import common.frontend.events.MouseEvtOnViewer;
import common.models.world2d.CommonDrawingOptions;
import common.models.world2d.CommonWorld2d;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

public abstract class CardsView extends ViewFx {
	
	@SuppressWarnings("rawtypes")
	protected abstract World2dResizableCanvasFx viewerCanvas();

	@SuppressWarnings("rawtypes")
	protected abstract World2dCanvasFx tabletopCanvas();

	protected abstract Pane dashboardContainer();

	protected abstract Pane cardsViewContainer();
	
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
		
		installMouseEvtOnViewer();
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

	@SuppressWarnings("unchecked")
	private void initializeTabletopCanvas() {
		double screenHeight = initialTabletopScreenHeight();
		double screenWidth = screenHeight * initialWorldWidth() / initialWorldHeight();

		tabletopCanvas().setWidth(screenWidth);
		tabletopCanvas().setHeight(screenHeight);

		tabletopCanvas().setWorldWidth(initialWorldWidth());
		tabletopCanvas().setWorldHeight(initialWorldHeight());

		tabletopCanvas().relocateResizeViewport(0, 0, initialWorldWidth(), initialWorldHeight());

		MouseEvtOnTabletop mouseEvtOnTabletop = NtroApp.newEvent(MouseEvtOnTabletop.class);
		

		tabletopCanvas().addMouseEventFilter(MouseEvent.ANY, world2dMouseEventFx -> {
			
			mouseEvtOnTabletop.setWorld2dMouseEventFx(world2dMouseEventFx);
			mouseEvtOnTabletop.trigger();

		});
		
	}

	@SuppressWarnings("unchecked")
	private void installMouseEvtOnViewer() {
		MouseEvtOnViewer mouseEvtOnViewer = NtroApp.newEvent(MouseEvtOnViewer.class);
		
		viewerCanvas().addMouseEventFilter(MouseEvent.ANY, world2dMouseEventFx -> {
			
			mouseEvtOnViewer.setWorld2dMouseEventFx(world2dMouseEventFx);
			mouseEvtOnViewer.trigger();

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
	public void displayWorld2d(CommonWorld2d world2d, CommonDrawingOptions options) {
		tabletopCanvas().displayWorld2d(world2d, options);
		viewerCanvas().displayWorld2d(world2d, options);
	}

	public void clearCanvas() {
		tabletopCanvas().clearCanvas();
		viewerCanvas().clearCanvas();
	}

	public void resizeViewport(double factor) {
		double oldWidth = viewerCanvas().viewportWidth();
		double oldHeight = viewerCanvas().viewportHeight();

		double newWidth = oldWidth * factor;
		double newHeight = oldHeight * factor;
		
		double newTopLeftX = viewerCanvas().viewportTopLeftX() - (newWidth - oldWidth) / 2;
		double newTopLeftY = viewerCanvas().viewportTopLeftY() - (newHeight - oldHeight) / 2;
		
		viewerCanvas().relocateResizeViewport(newTopLeftX,
				                              newTopLeftY, 
				                              newWidth, 
				                              newHeight);
		
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

	public void mouseEvtOnTabletop(World2dMouseEventFx world2dMouseEventFx) {
		MouseEvent evtFx = world2dMouseEventFx.rawMouseEvent();
		double worldX = world2dMouseEventFx.worldX();
		double worldY = world2dMouseEventFx.worldY();
		
		if(evtFx.getEventType().equals(MouseEvent.MOUSE_CLICKED)
				|| evtFx.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
			
			viewerCanvas().relocateViewport(worldX - viewerCanvas().viewportWidth() / 2, 
					                        worldY - viewerCanvas().viewportHeight() / 2);
		}
	}
}
