package ca.ntro.cards.models.world2d;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.World2dFx;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import javafx.scene.input.MouseEvent;

public abstract class CommonWorld2d extends World2dFx<CommonObject2d, CommonWorld2d> {
	
	private CommonCard2d movingCard = null;
	private double anchorX;
	private double anchorY;
	private EvtMoveViewport evtMoveViewport = NtroApp.newEvent(EvtMoveViewport.class);

	@Override
	protected void initialize() {
	}

	@Override
	protected void onMouseEventNotConsumed(MouseEvent evtFx, double worldX, double worldY) {
		if(movingCard != null 
				&& evtFx.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {

			movingCard.dragTo(worldX, worldY);

		}else if(movingCard != null 
				&& evtFx.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			
			forgetDraggedCard();

		}else if(movingCard == null 
				&& evtFx.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			
			anchorX = worldX;
			anchorY = worldY;

		}else if(movingCard == null 
				&& evtFx.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
			
			evtMoveViewport.setIncrementX(worldX - anchorX);
			evtMoveViewport.setIncrementY(worldY - anchorY);
			
			anchorX = worldX;
			anchorY = worldY;
			
			evtMoveViewport.trigger();
			
		}
	}

	private void forgetDraggedCard() {
		movingCard = null;
	}

	public void registerDraggedCard(CommonCard2d card2d) {
		this.movingCard = card2d;
	}

}
