package common.models.values;

import ca.ntro.app.NtroApp;

import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.app.frontend.views.elements.Color;
import ca.ntro.app.models.Value;
import ca.ntro.core.identifyers.Identifiable;
import common.models.enums.Suit;
import common.models.world2d.CommonDrawingOptions;

public class Card extends AbstractCard {
	
	private int rank = 2;
	private Suit suit = Suit.HEARTS;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getSuit() {
		return suit.name();
	}

	public void setSuit(String suit) {
		this.suit = Suit.valueOf(suit);
	}

	public Card() {
		super();
	}

	public Card(int rank, Suit suit) {
		super();
		setRank(rank);
		setSuit(suit.name());
	}


	@SuppressWarnings("rawtypes")
	private Color color(CommonDrawingOptions options) {
		return NtroApp.colorFromString(suit.color(options));
	}


	@SuppressWarnings("rawtypes")
	public void drawFaceUp(World2dGraphicsContext gc, 
			               double topLeftX, 
			               double topLeftY, 
			               double width, 
			               double height, 
			               int levelOfDetails, 
			               CommonDrawingOptions options) {

		if(levelOfDetails > 5) {

			drawFaceUpHighDetails(gc, topLeftX, topLeftY, width, height, options);

		}else {

			drawFaceUpLowDetails(gc, topLeftX, topLeftY, width, height, options);
			
		}
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void drawFaceUpHighDetails(World2dGraphicsContext gc, 
			                     double topLeftX, 
			                     double topLeftY, 
			                     double width, 
			                     double height, 
			                     CommonDrawingOptions options) {

		gc.setFill(NtroApp.colorFromString("#fff0db"));
		gc.fillRect(topLeftX, 
					topLeftY,
					width, 
					height);
		
		gc.setFill(color(options));
		gc.setStroke(color(options));

		gc.strokeText(String.valueOf(rank) + " " + suit.getSymbol(), topLeftX + 12, topLeftY + 24);
		gc.fillText(String.valueOf(rank) + " " + suit.getSymbol(), topLeftX + 12, topLeftY + 24);

		gc.strokeRect(topLeftX, 
					  topLeftY,
					  width, 
					  height);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void drawFaceUpLowDetails(World2dGraphicsContext gc, 
			                    double topLeftX, 
			                    double topLeftY, 
			                    double width, 
			                    double height, 
			                    CommonDrawingOptions options) {
		
		gc.setFill(color(options));

		gc.fillRect(topLeftX, 
					topLeftY,
					width, 
					height);
	}

	@Override
	public int compareTo(AbstractCard otherCard) {
		int result = 0;
		
		if(otherCard instanceof NullCard) {

			result = -1;

		}else {
			
			result = compareTo((Card) otherCard);
			
		}
		
		return result;

	}

	private int compareTo(Card otherCard) {
		int result = 0;
		
		if(suit.equals(otherCard.suit)) {

			result = Integer.compare(rank, otherCard.rank);

		}else {
			
			result = Integer.compare(suit.ordinal(), otherCard.suit.ordinal());
			
		}

		return result;

	}

	@Override
	public boolean isNullCard() {
		return false;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		format(builder);
		
		return builder.toString();
	}
	
	@Override
	public void format(StringBuilder builder) {
		builder.append(rank);
		builder.append(" of ");
		builder.append(suit);
		builder.append("  (");
		super.format(builder);
		builder.append(")");
	}

}
