package ca.ntro.cards.models.world2d;

import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.cards.ProcedureConstants;
import ca.ntro.cards.common.models.world2d.CommonObject2d;
import javafx.scene.input.MouseEvent;

public abstract class ProcedureObject2d<WORLD2D  extends ProcedureWorld2d<WORLD2D, OPTIONS>,
                                        OPTIONS  extends ProcedureDrawingOptions>

       extends        CommonObject2d<ProcedureObject2d<WORLD2D, OPTIONS>, WORLD2D, OPTIONS> {

	private double dragOffsetX;
	private double dragOffsetY;
	
	private static final double EPSILON = 1;
	private static final double INCREASE_SPEED_BELOW = 50;
	
	private double targetTopLeftX;
	private double targetTopLeftY;
	
	private int directionX = 0;
	private int directionY = 0;
	
	@Override
	public void initialize() {
		targetTopLeftX = topLeftX();
		targetTopLeftY = topLeftY();
		directionX = 0;
		directionY = 0;
		setSpeedX(0);
		setSpeedY(0);
	}

	public void setTarget(double targetTopLeftX, double targetTopLeftY) {
		this.targetTopLeftX = targetTopLeftX;
		this.targetTopLeftY = targetTopLeftY;
		
		double distanceToTargetX = Math.abs(targetTopLeftX - topLeftX());
		double distanceToTargetY = Math.abs(targetTopLeftY - topLeftY());

		directionX = Double.compare(targetTopLeftX, topLeftX());
		directionY = Double.compare(targetTopLeftY, topLeftY());
		
		setSpeedX(distanceToTargetX / ProcedureConstants.SECONDS_TO_REACH_TARGET * directionX);
		setSpeedY(distanceToTargetY / ProcedureConstants.SECONDS_TO_REACH_TARGET * directionY);

		checkIfTargetReached(1);
	}

	private void checkIfTargetReached(double speedFactor) {
		double distanceToTargetX = Math.abs(targetTopLeftX - topLeftX());
		double distanceToTargetY = Math.abs(targetTopLeftY - topLeftY());

		int nextDirectionX = Double.compare(targetTopLeftX, topLeftX());
		int nextDirectionY = Double.compare(targetTopLeftY, topLeftY());


		if(distanceToTargetX <= INCREASE_SPEED_BELOW
				|| distanceToTargetY <= INCREASE_SPEED_BELOW) {

			setSpeedX(getSpeedX() * speedFactor);
			setSpeedY(getSpeedY() * speedFactor);

		}
		
		if(distanceToTargetX <= EPSILON
				|| directionX != nextDirectionX) {
			
			reachTargetX();

		}

		if(distanceToTargetY <= EPSILON
				|| directionY != nextDirectionY) {
			
			reachTargetY();

		}
	}
	
	@Override 
	public void setTopLeftX(double topLeftX) {
		super.setTopLeftX(topLeftX);
		this.targetTopLeftX = topLeftX;
	}

	@Override 
	public void setTopLeftY(double topLeftY) {
		super.setTopLeftY(topLeftY);
		this.targetTopLeftY = topLeftY;
	}

	private void reachTargetY() {
		setTopLeftY(targetTopLeftY);
		setSpeedY(0);
		directionY = 0;
	}

	private void reachTargetX() {
		setTopLeftX(targetTopLeftX);
		setSpeedX(0);
		directionX = 0;
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
			
		}

		return false;
	}

	public void onTimePasses(double secondsElapsed) {
		super.onTimePasses(secondsElapsed);

		checkIfTargetReached(1 + 0.5 * Math.abs(0.5 - secondsElapsed));
	}

	public void dragTo(double worldX, double worldY) {
		setTopLeftX(worldX - dragOffsetX);
		setTopLeftY(worldY - dragOffsetY);
	}

	protected void onDragStarts() {
		getWorld().registerDraggedObject2d(this);
	}



}
