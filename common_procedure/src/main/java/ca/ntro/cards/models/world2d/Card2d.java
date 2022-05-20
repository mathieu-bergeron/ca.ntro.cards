package ca.ntro.cards.models.world2d;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.cards.common.messages.MsgFlipCard;
import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.AbstractCard;
import ca.ntro.cards.common.models.values.Card;
import ca.ntro.cards.common.models.values.NullCard;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.cards.common.models.world2d.CommonObject2d;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("rawtypes")
public abstract class   Card2d<OBJECT2D extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                               WORLD2D  extends ProcedureWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                               OPTIONS  extends ProcedureDrawingOptions>

                extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS> {
	
	private AbstractCard card;

	private double dragOffsetX;
	private double dragOffsetY;

	private MsgFlipCard msgFlipCard = NtroApp.newMessage(MsgFlipCard.class);

	public AbstractCard getCard() {
		return card;
	}

	public void setCard(AbstractCard card) {
		this.card = card;
	}

	public Card2d() {
		setCard(new NullCard());
	}

	public Card2d(int rank, Suit suit) {
		setCard(new Card(rank, suit));
	}

	public Card2d(AbstractCard card) {
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
	public void draw(World2dGraphicsContext gc, OPTIONS options) {
		card.draw(gc, 
				  getTopLeftX(), 
				  getTopLeftY(),
				  getWidth(),
				  getHeight(),
				  levelOfDetails(gc),
				  options);
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
		return card.isNullCard();
	}

}
