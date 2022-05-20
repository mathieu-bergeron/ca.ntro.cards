package ca.ntro.cards.models.enums;

import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.cards.models.world2d.ProcedureDrawingOptions;
import ca.ntro.core.initialization.Ntro;

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

	public String color(ProcedureDrawingOptions options) {
		String color = this.color;

		if(this == DIAMONDS
				&& !options.useFourCardColors()) {
			
			color = HEARTS.color;

		}else if(this == CLUBS
				&& !options.useFourCardColors()) {
			
			color = SPADES.color;

		}

		return color;
	}

	public static Suit random() {
		return Ntro.random().choice(Suit.values());
	}
}
