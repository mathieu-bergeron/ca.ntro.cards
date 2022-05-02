package ca.ntro.cards.playground.models.world2d;

import ca.ntro.cards.models.world2d.CommonWorld2d;

public class World2d extends CommonWorld2d {

	@Override
	protected void initialize() {
		super.initialize();

		Card2d card01 = new Card2d();
		Card2d card02 = new Card2d();
		Card2d card03 = new Card2d();

		addObject2d(card01);
		addObject2d(card02);
		addObject2d(card03);
		
		card01.setTopLeftX(50);
		card01.setTopLeftY(50);

		card02.setTopLeftX(150);
		card02.setTopLeftY(150);

		card03.setTopLeftX(250);
		card03.setTopLeftY(250);
	}

}
