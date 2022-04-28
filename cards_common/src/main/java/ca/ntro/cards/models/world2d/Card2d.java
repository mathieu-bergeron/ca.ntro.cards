package ca.ntro.cards.models.world2d;

import ca.ntro.app.views.controls.canvas.GraphicsContextFx;
import ca.ntro.app.world2d.Object2dDrawingOptions;
import ca.ntro.cards.models.values.Card;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Card2d extends Object2dCards {
	
	private Card card;
	
	private boolean moving = false;
	private double anchorX;
	private double anchorY;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@Override
	public void initialize() {
		setWidth(100);
		setHeight(100);
	}

	@Override
	protected boolean onMouseEvent(MouseEvent evtFx, double worldX, double worldY) {
		if(evtFx.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
			
			moving = true;
			
		} else if(evtFx.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {

			moving = false;
			
			
		} else if(moving 
				&& evtFx.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
			
			System.out.println("MOUSE_MOVED");
			
		}

		return true;
	}

	@Override
	public void draw(GraphicsContextFx gc, Object2dDrawingOptions options) {
		
		// TODO: do something meaningful here ;-)
		if(levelOfDetails(options) <= 5) {

			gc.strokeRect(getTopLeftX(), 
						  getTopLeftY(),
						  getWidth(), 
						  getHeight());
			
		}else {

			gc.getRawGraphicsContext().setFill(Color.CORAL);
			gc.fillRect(getTopLeftX(), 
						getTopLeftY(),
						getWidth(), 
						getHeight());
		}

	}
	
	private int levelOfDetails(Object2dDrawingOptions options) {
		int levelOfDetails = 10;
		
		if(options.screenWidth(getWidth()) <= 10
				|| options.screenHeight(getHeight()) <= 50) {
			
			levelOfDetails = 4;

		}

		return levelOfDetails;
	}


}
