package ca.ntro.cards.common.models.world2d;


import ca.ntro.app.NtroApp;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.app.world2d.World2dFx;
import ca.ntro.cards.common.frontend.events.EvtMoveViewport;
import javafx.scene.input.MouseEvent;

public abstract class   CommonWorld2d<OBJECT2D extends CommonObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                      WORLD2D extends CommonWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                      OPTIONS extends CommonDrawingOptions>

                extends World2dFx<OBJECT2D, WORLD2D, OPTIONS> {
	
	private double anchorX;
	private double anchorY;
	private EvtMoveViewport evtMoveViewport = NtroApp.newEvent(EvtMoveViewport.class);

	@Override
	protected void initialize() {
	}

	@Override
	protected void onMouseEventNotConsumed(World2dMouseEventFx mouseEvent) {
		MouseEvent evtFx = mouseEvent.rawMouseEvent();
		double worldX = mouseEvent.worldX();
		double worldY = mouseEvent.worldY();
		
	}





}
