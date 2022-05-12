package ca.ntro.cards.demo.models.world2d;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.models.values.AbstractCard;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.world2d.CommonObject2d;
import ca.ntro.cards.models.world2d.CommonWorld2d;
import ca.ntro.cards.demo.messages.MsgUpdateList;

public class World2d extends CommonWorld2d {
	
	private MsgUpdateList msgUpdateList = NtroApp.newMessage(MsgUpdateList.class);

	@Override
	protected void forgetDraggedCard() {
		super.forgetDraggedCard();
		
		List<CommonObject2d> leftRightObjects = new ArrayList<>(getObjects());
		
		leftRightObjects.sort((obj01, obj02) -> {
			return Double.compare(obj01.getTopLeftX(), obj02.getTopLeftX());
		});
		
		List<Card> leftRightCards = new ArrayList<>();
		
		for(CommonObject2d object2d : leftRightObjects) {
			if(object2d instanceof Card2d) {
				Card2d card2d = (Card2d) object2d;
				if(card2d.isNullCard()) {
					leftRightCards.add(null);
				}else {
					leftRightCards.add((Card) card2d.getCard());
				}
			}
		}
		
		msgUpdateList.setCards(leftRightCards);

		msgUpdateList.send();

	}

}
