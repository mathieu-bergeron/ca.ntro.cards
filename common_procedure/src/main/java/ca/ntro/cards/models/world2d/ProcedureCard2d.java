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

	private double dragOffsetX;
	private double dragOffsetY;
	
	private static final double EPSILON = 0.01;
	
	private double targetTopLeftX = -1;
	private double targetTopLeftY = -1;

	private double distanceToTargetX = 0;
	private double distanceToTargetY = 0;

	private int directionX = 0; 
	private int directionY = 0; 
	

	public AbstractCard getCard() {
		return card;
	}

	public void setCard(AbstractCard card) {
		this.card = card;
	}

	public void setTarget(double targetTopLeftX, double targetTopLeftY) {
		this.targetTopLeftX = targetTopLeftX;
		this.targetTopLeftY = targetTopLeftY;
		
		distanceToTargetX = Math.abs(targetTopLeftX - topLeftX());
		distanceToTargetY = Math.abs(targetTopLeftY - topLeftY());
		
		directionX = Double.compare(targetTopLeftX, topLeftX());
		directionY = Double.compare(targetTopLeftY, topLeftY());
		
		if(distanceToTargetX <= EPSILON) {
			reachTargetX(targetTopLeftX);
		}

		if(distanceToTargetY <= EPSILON) {
			reachTargetY(targetTopLeftY);
		}
	}

	private void reachTargetY(double targetTopLeftY) {
		setTopLeftY(targetTopLeftY);
		distanceToTargetY = 0;
	}

	private void reachTargetX(double targetTopLeftX) {
		setTopLeftX(targetTopLeftX);
		distanceToTargetX = 0;
		directionX = 0;
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
			
			flipCard();

		}

		return false;
	}

	private void flipCard() {
		this.faceUp = !faceUp;
	}

	public void dragTo(double worldX, double worldY) {
		setTopLeftX(worldX - dragOffsetX);
		setTopLeftY(worldY - dragOffsetY);
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

	private void moveTowardsTargetX(double secondsElapsed) {
		if(distanceToTargetX > EPSILON) {
			
			double decrementX = speed() * secondsElapsed;
			distanceToTargetX -= decrementX;
			setTopLeftX(topLeftX() + directionX * decrementX);

		}

		if(distanceToTargetX <= 0
				&& targetTopLeftX > 0) {

			reachTargetX(targetTopLeftX);
		}
	}
	
	private double speed() {
		return 500 + 1000/Math.max(distanceToTargetX, distanceToTargetY);

	}

	private void moveTowardsTargetY(double secondsElapsed) {
		if(distanceToTargetY > EPSILON) {
			
			double decrementY = speed() * secondsElapsed;
			distanceToTargetY -= decrementY;
			setTopLeftY(topLeftY() + directionY * decrementY);

		}

		if(distanceToTargetY <= 0
				&& targetTopLeftY > 0) {

			reachTargetY(targetTopLeftY);
		}
	}

	protected void onDragStarts() {
		getWorld().registerDraggedCard(this);

		targetTopLeftX = -1;
		targetTopLeftY = -1;
		distanceToTargetX = 0;
		distanceToTargetY = 0;
		directionX = 0;
		directionY = 0;
	}

	public void onTimePasses(double secondsElapsed) {

		moveTowardsTargetX(secondsElapsed);

		moveTowardsTargetY(secondsElapsed);
		
		super.onTimePasses(secondsElapsed);
	}

}
