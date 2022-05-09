package ca.ntro.cards.models.world2d;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.identifyers.IdFactory;
import ca.ntro.cards.models.values.Card;
import javafx.scene.input.MouseEvent;

public abstract class CommonCard2d extends CommonObject2d {
	
	private long nullId;
	private Card card;

	private double dragOffsetX;
	private double dragOffsetY;

	private MsgFlipCard msgFlipCard = NtroApp.newMessage(MsgFlipCard.class);

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
		if(card == null) {
			nullId = IdFactory.nextId();
		}
	}

	public CommonCard2d() {
		setCard(null);
	}

	public CommonCard2d(int rank, Suit suit) {
		setCard(new Card(rank, suit));
	}

	public CommonCard2d(Card card) {
		setCard(card);
	}

	protected abstract double initialWidth();
	protected abstract double initialHeight();

	@Override
	public String id() {
		String id = null;

		if(card != null) {

			id = card.id();

		}else {
			
			id = String.valueOf(nullId);
			
		}

		return id;
	}

	@Override
	public void initialize() {
		setWidth(initialWidth());
		setHeight(initialHeight());
	}

	@Override
	protected boolean onMouseEvent(World2dMouseEventFx mouseEvent) {
		MouseEvent evtFx = mouseEvent.rawMouseEvent();
		double worldX = mouseEvent.worldX();
		double worldY = mouseEvent.worldY();
		
		if(evtFx.getEventType().equals(MouseEvent.MOUSE_PRESSED)
				&& evtFx.isPrimaryButtonDown()) {
			
			dragOffsetX = worldX - getTopLeftX();
			dragOffsetY = worldY - getTopLeftY();
			
			onDragStarts();
			
			return true;
			
		}else if(evtFx.getEventType().equals(MouseEvent.MOUSE_PRESSED)
				&& evtFx.isSecondaryButtonDown()) {
			
			msgFlipCard.setCardId(getCard().id());
			msgFlipCard.send();
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
	public void draw(World2dGraphicsContext gc, CommonDrawingOptions options) {
		if(isNullCard()) {

			
			gc.setFill(NtroApp.colorFromString("#ffffff"));

			gc.fillRect(getTopLeftX(), 
					      getTopLeftY(),
					      getWidth(),
					      getHeight());

			gc.strokeRect(getTopLeftX(), 
					      getTopLeftY(),
					      getWidth(),
					      getHeight());

			/*
			gc.strokeArc(getTopLeftX() + getWidth()/2 - getWidth()/8, 
					     getTopLeftY() + getHeight()/2 - getWidth()/8,
					     getWidth()/4,
					     getWidth()/4,
					     0,
					     360);
					     */
			
			/*
			gc.fillText("null", 
					    getTopLeftX() + getWidth() / 2 - 16, 
					    getTopLeftY() + getHeight() / 2);
			*/


		}else {

			card.draw(gc, 
					  getTopLeftX(), 
					  getTopLeftY(),
					  getWidth(),
					  getHeight(),
					  levelOfDetails(gc),
					  options);
		}
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

	public boolean isNullCard() {
		return card == null;
	}

}
