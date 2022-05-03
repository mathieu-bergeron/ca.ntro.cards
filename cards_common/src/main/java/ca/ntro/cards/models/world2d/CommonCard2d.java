package ca.ntro.cards.models.world2d;

import ca.ntro.app.views.controls.canvas.World2dGraphicsContextFx;
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
		if(evtFx.getEventType().equals(MouseEvent.MOUSE_PRESSED)
				&& evtFx.isPrimaryButtonDown()) {
			
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
	public void draw(World2dGraphicsContextFx<CommonObject2d, CommonWorld2d> gc) {

		if(levelOfDetails(gc) <= 5) {

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
	
	private int levelOfDetails(World2dGraphicsContextFx<CommonObject2d, CommonWorld2d> gc) {
		int levelOfDetails = 10;

		if(gc.widthOnScreen(getWidth()) <= 10
				|| gc.heightOnScreen(getHeight()) <= 30) {
			
			levelOfDetails = 4;

		}

		return levelOfDetails;
	}


}
