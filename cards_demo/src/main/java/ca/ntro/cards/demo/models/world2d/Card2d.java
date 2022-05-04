package ca.ntro.cards.demo.models.world2d;

import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.world2d.CommonCard2d;
import ca.ntro.cards.demo.DemoConstants;

public class Card2d extends CommonCard2d {
	
	private static final double EPSILON = 0.001;
	private static final double SECONDS_TO_TARGET = 1;
	
	private double targetTopLeftX = -1;
	private double targetTopLeftY = -1;

	public Card2d(Card card) {
		super(card);
	}

	public Card2d(long id, int rank, Suit suit) {
		super(id, rank, suit);
	}

	@Override
	protected double initialWidth() {
		return DemoConstants.INITIAL_CARD_WIDTH_MILIMETERS;
	}

	@Override
	protected double initialHeight() {
		return DemoConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
	}

	public void setTargetTopLeftX(double targetTopLeftX) {
		this.targetTopLeftX = targetTopLeftX;
	}

	public void setTargetTopLeftY(double targetTopLeftY) {
		this.targetTopLeftY = targetTopLeftY;
	}

	public void onTimePasses(double secondsElapsed) {
		if(targetTopLeftX != -1
				&& Math.abs(targetTopLeftX - topLeftX()) > EPSILON) {
			
			setTopLeftX(topLeftX() + (targetTopLeftX - topLeftX()) * secondsElapsed / SECONDS_TO_TARGET);

		}else {

			targetTopLeftX = -1;

		}

		if(targetTopLeftY != -1
				&& Math.abs(targetTopLeftY - topLeftY()) > EPSILON) {
			
			setTopLeftY(topLeftY() + (targetTopLeftY - topLeftY()) * secondsElapsed / SECONDS_TO_TARGET);

		}else {

			targetTopLeftY = -1;

		}
		
		super.onTimePasses(secondsElapsed);
	}

	@Override
	protected void onDragStarts() {
		targetTopLeftX = -1;
		targetTopLeftY = -1;
		super.onDragStarts();
	}


}
