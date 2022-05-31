package ca.ntro.cards.models.world2d;

import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.cards.ProcedureConstants;
import ca.ntro.cards.common.models.world2d.CommonObject2d;
import javafx.scene.input.MouseEvent;

public abstract class ProcedureObject2d<OBJECT2D extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                        WORLD2D  extends ProcedureWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                        OPTIONS  extends ProcedureDrawingOptions>

       extends        CommonObject2d<OBJECT2D, WORLD2D, OPTIONS> {

	private double dragOffsetX;
	private double dragOffsetY;
	
	private static final double EPSILON = 0.01;
	
	private double targetTopLeftX = -1;
	private double targetTopLeftY = -1;

	public void setTarget(double targetTopLeftX, double targetTopLeftY) {
		this.targetTopLeftX = targetTopLeftX;
		this.targetTopLeftY = targetTopLeftY;

		reachTargetOrAdjustSpeed();
	}

	private void reachTargetOrAdjustSpeed() {
		double distanceToTargetX = Math.abs(targetTopLeftX - topLeftX());
		double distanceToTargetY = Math.abs(targetTopLeftY - topLeftY());
		
		if(distanceToTargetX <= EPSILON) {

			reachTargetX(targetTopLeftX);

		}else {
			
			double directionX = Double.compare(targetTopLeftX, topLeftX());
			setSpeedX(distanceToTargetX / ProcedureConstants.SECONDS_TO_REACH_TARGET * directionX);
			
		}

		if(distanceToTargetY <= EPSILON) {

			reachTargetY(targetTopLeftY);

		}else {
			
			double directionY = Double.compare(targetTopLeftY, topLeftY());
			setSpeedY(distanceToTargetY / ProcedureConstants.SECONDS_TO_REACH_TARGET * directionY);

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

	private void reachTargetY(double targetTopLeftY) {
		setTopLeftY(targetTopLeftY);
	}

	private void reachTargetX(double targetTopLeftX) {
		setTopLeftX(targetTopLeftX);
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

		reachTargetOrAdjustSpeed();
	}

	public void dragTo(double worldX, double worldY) {
		setTopLeftX(worldX - dragOffsetX);
		setTopLeftY(worldY - dragOffsetY);
	}

	protected void onDragStarts() {
		getWorld().registerDraggedObject2d(this);

		targetTopLeftX = -1;
		targetTopLeftY = -1;
	}



}
