package ca.ntro.cards.models.values;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.app.frontend.views.elements.Color;
import ca.ntro.app.models.Value;
import ca.ntro.cards.models.enums.Suit;
import ca.ntro.core.identifyers.Identifiable;

public class Card implements Value, Identifiable {
	
	private static long nextId = 1;
	
	private long id;
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
	
	public Card() {
		generateId();
	}

	public Card(int rank, Suit suit) {
		generateId();

		setRank(rank);
		setSuit(suit.name());
	}

	@Override
	public String id() {
		return String.valueOf(id);
	}
	
	private void generateId() {
		this.id = nextId;
		nextId++;
	}

	@SuppressWarnings("rawtypes")
	private Color color() {
		return NtroApp.colorFromString(suit.getColor());
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
		
		gc.setFill(color());
		gc.setStroke(color());

		gc.strokeText(String.valueOf(rank) + " " + suit.getSymbol(), topLeftX + 12, topLeftY + 24);
		gc.fillText(String.valueOf(rank) + " " + suit.getSymbol(), topLeftX + 12, topLeftY + 24);

		gc.strokeRect(topLeftX, 
					  topLeftY,
					  width, 
					  height);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void drawLowDetails(World2dGraphicsContext gc, 
			                    double topLeftX, 
			                    double topLeftY, 
			                    double width, 
			                    double height) {
		
		gc.setFill(color());

		gc.fillRect(topLeftX, 
					topLeftY,
					width, 
					height);
	}


}
