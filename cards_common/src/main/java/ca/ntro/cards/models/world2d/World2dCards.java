package ca.ntro.cards.models.world2d;

import ca.ntro.app.world2d.World2dFx;
import javafx.scene.input.MouseEvent;

public class World2dCards extends World2dFx<Object2dCards, World2dCards> {
	
	public static final double INITIAL_WORLD_WIDTH = 6400;
	public static final double INITIAL_WORLD_HEIGHT = 3200;
	
	private Card2d movingCard = null;

	@Override
	protected void initialize() {
		Card2d card01 = new Card2d();
		Card2d card02 = new Card2d();
		Card2d card03 = new Card2d();

		addObject2d(card01);
		addObject2d(card02);
		addObject2d(card03);
		
		card01.setTopLeftX(50);
		card01.setTopLeftY(50);

		card02.setTopLeftX(150);
		card02.setTopLeftY(150);

		card03.setTopLeftX(250);
		card03.setTopLeftY(250);
	}

	@Override
	protected void onMouseEventNotConsumed(MouseEvent evtFx, double worldX, double worldY) {
		if(movingCard != null 
				&& evtFx.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {

			movingCard.dragTo(worldX, worldY);

		}else if(movingCard != null 
				&& evtFx.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			
			forgetMovingCard();

		}
	}

	private void forgetMovingCard() {
		movingCard = null;
	}

	public void registerMovingCard(Card2d card2d) {
		this.movingCard = card2d;
	}

}
