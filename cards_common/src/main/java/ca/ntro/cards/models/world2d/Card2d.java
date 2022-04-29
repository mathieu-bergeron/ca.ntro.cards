package ca.ntro.cards.models.world2d;

import ca.ntro.app.views.controls.canvas.GraphicsContextFx;
import ca.ntro.app.world2d.Object2dDrawingOptions;
import ca.ntro.cards.models.values.Card;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Card2d extends Object2dCards {
	
	private Card card;
	
	private double dragOffsetX;
	private double dragOffsetY;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@Override
	public void initialize() {
		setWidth(30);
		setHeight(90);
	}

	@Override
	protected boolean onMouseEvent(MouseEvent evtFx, double worldX, double worldY) {
		if(evtFx.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			
			dragOffsetX = worldX - getTopLeftX();
			dragOffsetY = worldY - getTopLeftY();
			
			getWorld().registerDraggedCard(this);
			
			return true;
			
		} 

		return false;
	}

	public void dragTo(double worldX, double worldY) {
		setTopLeftX(worldX - dragOffsetX);
		setTopLeftY(worldY - dragOffsetY);
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
