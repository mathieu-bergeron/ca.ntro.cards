package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.cards.common.frontend.views.CommonCanvasView;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.cards.common.models.world2d.CommonWorld2d;
import ca.ntro.cards.frontend.events.MouseEvtOnPreviewCanvas;
import javafx.scene.input.MouseEvent;

public abstract class ProcedureCanvasView extends CommonCanvasView {

	@SuppressWarnings("rawtypes")
	protected abstract World2dCanvasFx previewCanvas();

	protected abstract double initialPreviewCanvasScreenHeight();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);

		initializePreviewCanvas();
	}

	@SuppressWarnings("unchecked")
	private void initializePreviewCanvas() {

		double screenHeight = initialPreviewCanvasScreenHeight();
		double screenWidth = screenHeight * initialWorldWidth() / initialWorldHeight();

		previewCanvas().setWidth(screenWidth);
		previewCanvas().setHeight(screenHeight);

		previewCanvas().setWorldWidth(initialWorldWidth());
		previewCanvas().setWorldHeight(initialWorldHeight());

		previewCanvas().relocateResizeViewport(0, 0, initialWorldWidth(), initialWorldHeight());

		MouseEvtOnPreviewCanvas mouseEvtOnTabletop = NtroApp.newEvent(MouseEvtOnPreviewCanvas.class);
		
		previewCanvas().addMouseEventFilter(MouseEvent.ANY, world2dMouseEventFx -> {
			
			mouseEvtOnTabletop.setWorld2dMouseEventFx(world2dMouseEventFx);
			mouseEvtOnTabletop.trigger();

		});
	}

	@SuppressWarnings("unchecked")
	public void displayWorld2d(CommonWorld2d world2d, CommonDrawingOptions options) {
		super.displayWorld2d(world2d, options);
		previewCanvas().displayWorld2d(world2d, options);
	}

	public void clearCanvas() {
		super.clearCanvas();
		previewCanvas().clearCanvas();
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

}
