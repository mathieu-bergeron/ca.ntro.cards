package ca.ntro.cards.models.world2d;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.cards.common.frontend.events.EvtMoveViewport;
import ca.ntro.cards.common.models.world2d.CommonWorld2d;
import javafx.scene.input.MouseEvent;

public class ProcedureWorld2d<OBJECT2D extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                              WORLD2D  extends ProcedureWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                              OPTIONS  extends ProcedureDrawingOptions>

       extends CommonWorld2d<OBJECT2D, WORLD2D, OPTIONS> {

	private ProcedureCard2d<OBJECT2D, WORLD2D, OPTIONS> movingCard = null;
	private double anchorX;
	private double anchorY;
	private EvtMoveViewport evtMoveViewport = NtroApp.newEvent(EvtMoveViewport.class);

	@Override
	protected void onMouseEventNotConsumed(World2dMouseEventFx mouseEvent) {
		MouseEvent evtFx = mouseEvent.rawMouseEvent();
		double worldX = mouseEvent.worldX();
		double worldY = mouseEvent.worldY();

		if(movingCard != null 
				&& evtFx.getEventType().equals(MouseEvent.MOUSE_DRAGGED)
				&& evtFx.isPrimaryButtonDown()) {

			movingCard.dragTo(worldX, worldY);

		}else if(movingCard != null 
				&& evtFx.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			
			forgetDraggedCard();

		}else if(movingCard == null 
				&& evtFx.getEventType().equals(MouseEvent.MOUSE_PRESSED)
				&& evtFx.isMiddleButtonDown()) {
			
			anchorX = evtFx.getX();
			anchorY = evtFx.getY();

		}else if(movingCard == null 
				&& evtFx.getEventType().equals(MouseEvent.MOUSE_DRAGGED)
				&& evtFx.isMiddleButtonDown()) {
			
			evtMoveViewport.setIncrementX(mouseEvent.widthInWorld(anchorX - evtFx.getX()));
			evtMoveViewport.setIncrementY(mouseEvent.heightInWorld(anchorY - evtFx.getY()));

			anchorX = evtFx.getX();
			anchorY = evtFx.getY();
			
			evtMoveViewport.trigger();
		}
	}

	protected void forgetDraggedCard() {
		movingCard = null;
	}

	public void registerDraggedCard(ProcedureCard2d<OBJECT2D, WORLD2D, OPTIONS> card2d) {
		this.movingCard = card2d;
	}
}
