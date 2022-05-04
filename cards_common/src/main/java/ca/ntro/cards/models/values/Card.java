package ca.ntro.cards.models.values;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.app.models.Value;
import ca.ntro.cards.models.enums.Suit;

public class Card implements Value {
	
	private int rank;
	private Suit suit;

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

	public Card(int rank, Suit suit) {
		setRank(rank);
		setSuit(suit.name());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setFill(World2dGraphicsContext gc) {
		gc.setFill(NtroApp.colorFromString(suit.getColor()));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setStroke(World2dGraphicsContext gc) {
		gc.setStroke(NtroApp.colorFromString(suit.getColor()));
	}

	@SuppressWarnings("rawtypes")
	public void draw(World2dGraphicsContext gc, 
			         double topLeftX, 
			         double topLeftY, 
			         double width, 
			         double height, 
			         int levelOfDetails) {

		if(levelOfDetails <= 5) {
			
			drawLowDetails(gc, topLeftX, topLeftY, width, height);
			
		}else {

			drawHighDetails(gc, topLeftX, topLeftY, width, height);

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void drawHighDetails(World2dGraphicsContext gc, 
			                     double topLeftX, 
			                     double topLeftY, 
			                     double width, 
			                     double height) {

		gc.setFill(NtroApp.colorFromString("#fff0db"));
		gc.fillRect(topLeftX, 
					topLeftY,
					width, 
					height);

		setStroke(gc);
		setFill(gc);

		gc.strokeText(String.valueOf(rank), topLeftX + 12, topLeftY + 24);
		gc.fillText(suit.getSymbol(), topLeftX + 12 + String.valueOf(rank).length() * 8, topLeftY + 24);

		gc.strokeRect(topLeftX, 
					  topLeftY,
					  width, 
					  height);
	}

	@SuppressWarnings("rawtypes")
	private void drawLowDetails(World2dGraphicsContext gc, 
			                    double topLeftX, 
			                    double topLeftY, 
			                    double width, 
			                    double height) {
		
		setFill(gc);

		gc.fillRect(topLeftX, 
					topLeftY,
					width, 
					height);
	}

}
