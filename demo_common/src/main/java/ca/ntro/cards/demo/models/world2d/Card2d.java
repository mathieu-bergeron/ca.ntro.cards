package ca.ntro.cards.demo.models.world2d;

import common.models.enums.Suit;
import common.models.values.AbstractCard;
import common.models.values.Card;
import common.models.world2d.CommonCard2d;
import ca.ntro.cards.demo.DemoConstants;

public class Card2d extends CommonCard2d {

	private static final double EPSILON = 0.01;
	
	private double targetTopLeftX = -1;
	private double targetTopLeftY = -1;

	private double distanceToTargetX = 0;
	private double distanceToTargetY = 0;

	private int directionX = 0; 
	private int directionY = 0; 


	public Card2d() {
		super();
	}

	public Card2d(AbstractCard card) {
		super(card);
	}

	public Card2d(int rank, Suit suit) {
		super(rank, suit);
	}

	@Override
	protected double initialWidth() {
		return DemoConstants.INITIAL_CARD_WIDTH_MILIMETERS;
	}

	@Override
	protected double initialHeight() {
		return DemoConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
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

	public void onTimePasses(double secondsElapsed) {

		moveTowardsTargetX(secondsElapsed);

		moveTowardsTargetY(secondsElapsed);
		
		super.onTimePasses(secondsElapsed);
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

	@Override
	protected void onDragStarts() {
		targetTopLeftX = -1;
		targetTopLeftY = -1;
		distanceToTargetX = 0;
		distanceToTargetY = 0;
		directionX = 0;
		directionY = 0;
		super.onDragStarts();
	}

}
