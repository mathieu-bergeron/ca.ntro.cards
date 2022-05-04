package ca.ntro.cards.models.world2d;

import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.values.Card;
import javafx.scene.input.MouseEvent;

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

	public CommonCard2d(long id, int rank, Suit suit) {
		setCard(new Card(id, rank, suit));
	}

	public CommonCard2d(Card card) {
		setCard(card);
	}

	protected abstract double initialWidth();
	protected abstract double initialHeight();

	@Override
	public String id() {
		return card.id();
	}

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
			
			onDragStarts();
			
			return true;
			
		} 

		return false;
	}

	protected void onDragStarts() {
		getWorld().registerDraggedCard(this);
	}

	public void dragTo(double worldX, double worldY) {
		setTopLeftX(worldX - dragOffsetX);
		setTopLeftY(worldY - dragOffsetY);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void draw(World2dGraphicsContext gc) {
		card.draw(gc, 
				  getTopLeftX(), 
				  getTopLeftY(),
				  getWidth(),
				  getHeight(),
				  levelOfDetails(gc));
	}
	
	@SuppressWarnings("rawtypes")
	private int levelOfDetails(World2dGraphicsContext gc) {
		int levelOfDetails = 10;

		if(gc.widthOnScreen(getWidth()) <= 10
				|| gc.heightOnScreen(getHeight()) <= 30) {
			
			levelOfDetails = 4;

		}

		return levelOfDetails;
	}


}
