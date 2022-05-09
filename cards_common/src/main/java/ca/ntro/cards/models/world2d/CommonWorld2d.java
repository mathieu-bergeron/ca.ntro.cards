package ca.ntro.cards.models.world2d;


import java.util.Set;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.app.world2d.World2dFx;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import javafx.scene.input.MouseEvent;

public abstract class CommonWorld2d extends World2dFx<CommonObject2d, CommonWorld2d, CommonDrawingOptions> {
	
	private CommonCard2d movingCard = null;
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

	public void registerDraggedCard(CommonCard2d card2d) {
		this.movingCard = card2d;
	}




}
