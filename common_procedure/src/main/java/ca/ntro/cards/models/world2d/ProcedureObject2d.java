package ca.ntro.cards.models.world2d;

import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
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

	private double distanceToTargetX = 0;
	private double distanceToTargetY = 0;

	private int directionX = 0; 
	private int directionY = 0; 

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

		moveTowardsTargetX(secondsElapsed);

		moveTowardsTargetY(secondsElapsed);
		
		super.onTimePasses(secondsElapsed);
	}

	public void dragTo(double worldX, double worldY) {
		setTopLeftX(worldX - dragOffsetX);
		setTopLeftY(worldY - dragOffsetY);
	}

	protected void onDragStarts() {
		getWorld().registerDraggedObject2d(this);

		targetTopLeftX = -1;
		targetTopLeftY = -1;
		distanceToTargetX = 0;
		distanceToTargetY = 0;
		directionX = 0;
		directionY = 0;
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


}
