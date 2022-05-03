package ca.ntro.cards.models.world2d;

import ca.ntro.app.views.controls.canvas.GraphicsContextFx;
import ca.ntro.app.world2d.Object2dDrawingOptions;
import ca.ntro.cards.models.values.Card;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class CommonCard2d extends CommonObject2d {
	
	private Card card;
	
	private double dragOffsetX;
	private double dragOffsetY;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	protected abstract double initialWidth();
	protected abstract double initialHeight();

	@Override
	public void initialize() {
		setWidth(initialWidth());
		setHeight(initialHeight());
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
		
		if(levelOfDetails(options) <= 5) {

			gc.getRawGraphicsContext().setFill(Color.CORAL);
			gc.fillRect(getTopLeftX(), 
						getTopLeftY(),
						getWidth(), 
						getHeight());

			
		}else {

			gc.strokeRect(getTopLeftX(), 
						  getTopLeftY(),
						  getWidth(), 
						  getHeight());

		}

	}
	
	private int levelOfDetails(Object2dDrawingOptions options) {
		int levelOfDetails = 10;

		if(options.widthOnScreen(getWidth()) <= 10
				|| options.heightOnScreen(getHeight()) <= 30) {
			
			levelOfDetails = 4;

		}

		return levelOfDetails;
	}


}
