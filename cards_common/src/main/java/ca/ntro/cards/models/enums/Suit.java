package ca.ntro.cards.models.enums;

public enum Suit {
	
	HEARTS("♥", "#e6194B"), DIAMONDS("♦", "#4363d8"), CLUBS("♣", "#3cb44b"), SPADES("♠", "#000000");

	private String symbol;
	private String color;
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	Suit(String symbol, String color){
		setSymbol(symbol);
		setColor(color);
	}
}
