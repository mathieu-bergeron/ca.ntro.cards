package ca.ntro.cards.models.world2d;

import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.models.values.cards.NullCard;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("rawtypes")
public abstract class   ProcedureCard2d<OBJECT2D extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                        WORLD2D  extends ProcedureWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                        OPTIONS  extends ProcedureDrawingOptions>

                extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS> {
	
	private boolean faceUp = true;
	
	private AbstractCard card;

	

	public AbstractCard getCard() {
		return card;
	}

	public void setCard(AbstractCard card) {
		this.card = card;
	}


	public ProcedureCard2d() {
		setCard(new NullCard());
	}

	public ProcedureCard2d(int rank, Suit suit) {
		setCard(new Card(rank, suit));
	}

	public ProcedureCard2d(AbstractCard card) {
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


	protected void flipCard() {
		this.faceUp = !faceUp;

	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void draw(World2dGraphicsContext gc, OPTIONS options) {
		if(faceUp) {

			card.drawFaceUp(gc, 
					        getTopLeftX(), 
					        getTopLeftY(),
					        getWidth(),
					        getHeight(),
					        levelOfDetails(gc),
					        options);
			
		}else {

			card.drawFaceDown(gc, 
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
		return card.isNullCard();
	}

	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}

	@Override
	protected boolean onMouseEvent(World2dMouseEventFx mouseEvent) {
		if(!super.onMouseEvent(mouseEvent)) {

			MouseEvent evtFx = mouseEvent.rawMouseEvent();

			if(evtFx.getEventType().equals(MouseEvent.MOUSE_PRESSED) 
					&& evtFx.isSecondaryButtonDown()) {
				
				flipCard();
				getWorld().buildAndSendManualModel();
				return true;
			}
		}
		
		return false;
	}




}
