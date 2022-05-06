package ca.ntro.cards.models.values;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.app.frontend.views.elements.Color;
import ca.ntro.app.models.Value;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.world2d.CommonDrawingOptions;
import ca.ntro.core.identifyers.Identifiable;

public class Card implements Value, Identifiable {
	
	private long id;
	private int rank;
	private Suit suit;
	private boolean faceUp = true;

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
		setId(CardsModel.nextId());
	}

	public Card(int rank, Suit suit) {
		setId(CardsModel.nextId());
		setRank(rank);
		setSuit(suit.name());
	}

	@Override
	public String id() {
		return String.valueOf(getId());
	}

	@SuppressWarnings("rawtypes")
	private Color color(CommonDrawingOptions options) {
		return NtroApp.colorFromString(suit.color(options));
	}

	@SuppressWarnings("rawtypes")
	public void draw(World2dGraphicsContext gc, 
			         double topLeftX, 
			         double topLeftY, 
			         double width, 
			         double height, 
			         int levelOfDetails, 
			         CommonDrawingOptions options) {
		
		if(faceUp) {

			drawFaceUp(gc, topLeftX, topLeftY, width, height, levelOfDetails, options);

		}else {

			drawFaceDown(gc, topLeftX, topLeftY, width, height, levelOfDetails, options);
			
		}
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

	@SuppressWarnings("rawtypes")
	public void drawFaceDown(World2dGraphicsContext gc, 
			                 double topLeftX, 
			                 double topLeftY, 
			                 double width, 
			                 double height, 
			                 int levelOfDetails, 
			                 CommonDrawingOptions options) {

		if(levelOfDetails > 5) {

			drawFaceDownHighDetails(gc, topLeftX, topLeftY, width, height, options);

		}else {

			drawFaceDownLowDetails(gc, topLeftX, topLeftY, width, height, options);

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void drawFaceDownHighDetails(World2dGraphicsContext gc, 
			                  double topLeftX, 
			                  double topLeftY, 
			                  double width, 
			                  double height, 
			                  CommonDrawingOptions options) {
		
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
			                            double height, CommonDrawingOptions options) {
		
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
