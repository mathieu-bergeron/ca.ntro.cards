package ca.ntro.cards;

public class CommonConstants {
	
	public static final double INITIAL_CARD_WIDTH_MILIMETERS = 50;    // CARD_HEIGHT_MILIMETERS * 2/3
	public static final double INITIAL_CARD_HEIGHT_MILIMETERS = 75;
	
	public static final int INITIAL_WORLD_WIDTH_IN_CARDS = 30;
	public static final int INITIAL_WORLD_HEIGHT_IN_CARDS = INITIAL_WORLD_WIDTH_IN_CARDS;

	public static final double INITIAL_WORLD_WIDTH = INITIAL_WORLD_WIDTH_IN_CARDS * INITIAL_CARD_WIDTH_MILIMETERS;
	public static final double INITIAL_WORLD_HEIGHT = INITIAL_WORLD_HEIGHT_IN_CARDS * INITIAL_CARD_HEIGHT_MILIMETERS;   
	
	public static final double INITIAL_TABLETOP_SCREEN_HEIGHT = 200;
	
	public static final String RED = "#e6194B";
	public static final String BLUE = "#4363d8";
	public static final String GREEN = "#3cb44b";
	public static final String BLACK = "#000000";
	
	public static final double SECONDS_BETWEEN_EXECUTION_STEPS = 0.075;

}
