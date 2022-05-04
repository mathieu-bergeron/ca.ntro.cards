package ca.ntro.cards.playground.models.world2d;

import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.world2d.CommonWorld2d;

public class World2d extends CommonWorld2d {

	@Override
	protected void initialize() {
		super.initialize();

		Card2d card01 = new Card2d(2, Suit.HEARTS);
		Card2d card02 = new Card2d(10, Suit.DIAMONDS);
		Card2d card03 = new Card2d(6, Suit.CLUBS);
		Card2d card04 = new Card2d(4, Suit.SPADES);

		addObject2d(card01);
		addObject2d(card02);
		addObject2d(card03);
		addObject2d(card04);
		
		card01.setTopLeftX(50);
		card01.setTopLeftY(50);

		card02.setTopLeftX(150);
		card02.setTopLeftY(50);

		card03.setTopLeftX(250);
		card03.setTopLeftY(50);

		card04.setTopLeftX(350);
		card04.setTopLeftY(50);
	}

}
