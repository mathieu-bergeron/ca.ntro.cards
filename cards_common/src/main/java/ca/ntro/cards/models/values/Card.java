package ca.ntro.cards.models.values;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.app.frontend.views.elements.Color;
import ca.ntro.app.models.Value;
import ca.ntro.cards.models.enums.Suit;
import ca.ntro.core.identifyers.Identifiable;

public class Card implements Value, Identifiable {
	
	private long id;
	private int rank;
	private Suit suit;
	private boolean faceUp = false;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getFaceUp() {
		return faceUp;
	}

	public void setFaceUp(boolean facesUp) {
		this.faceUp = facesUp;
	}

	public Card() {
	}

	public Card(long id, int rank, Suit suit) {
		setId(id);
		setRank(rank);
		setSuit(suit.name());
	}

	@Override
	public String id() {
		return String.valueOf(getId());
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
		
		if(faceUp) {

			drawFaceUp(gc, topLeftX, topLeftY, width, height, levelOfDetails);

		}else {

			drawFaceDown(gc, topLeftX, topLeftY, width, height, levelOfDetails);
			
		}
	}

	@SuppressWarnings("rawtypes")
	public void drawFaceUp(World2dGraphicsContext gc, 
			               double topLeftX, 
			               double topLeftY, 
			               double width, 
			               double height, 
			               int levelOfDetails) {

		if(levelOfDetails > 5) {

			drawFaceUpHighDetails(gc, topLeftX, topLeftY, width, height);

		}else {

			drawFaceUpLowDetails(gc, topLeftX, topLeftY, width, height);
			
		}
	}

	@SuppressWarnings("rawtypes")
	public void drawFaceDown(World2dGraphicsContext gc, 
			                 double topLeftX, 
			                 double topLeftY, 
			                 double width, 
			                 double height, 
			                 int levelOfDetails) {

		if(levelOfDetails > 5) {

			drawFaceDownHighDetails(gc, topLeftX, topLeftY, width, height);

		}else {

			drawFaceDownLowDetails(gc, topLeftX, topLeftY, width, height);

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void drawFaceUpHighDetails(World2dGraphicsContext gc, 
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
	private void drawFaceUpLowDetails(World2dGraphicsContext gc, 
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void drawFaceDownHighDetails(World2dGraphicsContext gc, 
			                  double topLeftX, 
			                  double topLeftY, 
			                  double width, 
			                  double height) {
		
		gc.setFill(NtroApp.colorFromString("#999999"));
		gc.fillRect(topLeftX, 
					topLeftY,
					width, 
					height);

		gc.setStroke(NtroApp.colorFromString("#000000"));
		gc.strokeRect(topLeftX, 
					  topLeftY,
					  width, 
					  height);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void drawFaceDownLowDetails(World2dGraphicsContext gc, 
			                            double topLeftX, 
			                            double topLeftY, 
			                            double width, 
			                            double height) {
		
		gc.setFill(NtroApp.colorFromString("#aaaaaa"));
		gc.fillRect(topLeftX, 
					topLeftY,
					width, 
					height);
	}

	public boolean hasId(String id) {
		return String.valueOf(this.id).equals(id);
	}

	public void flip() {
		this.faceUp = !this.faceUp;
	}

}
