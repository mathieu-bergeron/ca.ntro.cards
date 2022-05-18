package ca.ntro.cards.demo.models.world2d;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.world2d.CommonWorld2d;
import ca.ntro.cards.CommonConstants;
import ca.ntro.cards.demo.messages.DemoMsgRegisterSimpleOperation;
import ca.ntro.cards.demo.messages.MsgUpdateList;

public class World2d extends CommonWorld2d {
	
	private MsgUpdateList msgUpdateList = NtroApp.newMessage(MsgUpdateList.class);
	private DemoMsgRegisterSimpleOperation msgRegisterSimpleOperation = NtroApp.newMessage(DemoMsgRegisterSimpleOperation.class);

	@SuppressWarnings("rawtypes")
	@Override
	protected void forgetDraggedCard() {
		super.forgetDraggedCard();
		
		List<Card2d> topCards2d = new ArrayList<>();
		List<Card2d> bottomCards2d = new ArrayList<>();
		
		for(Object2d object2d : getObjects()) {

			if(object2d instanceof Card2d) {

				if(object2d.topLeftY() < CommonConstants.INITIAL_CARD_HEIGHT_MILIMETERS) {
					
					topCards2d.add((Card2d) object2d);

				}else {

					bottomCards2d.add((Card2d) object2d);
				}
			}
		}
		
		topCards2d.sort((obj01, obj02) -> {
			return Double.compare(obj01.getTopLeftX(), obj02.getTopLeftX());
		});

		bottomCards2d.sort((obj01, obj02) -> {
			return Double.compare(obj01.getTopLeftX(), obj02.getTopLeftX());
		});
		
		List<Card> sourceList = new ArrayList<>();
		List<Card> targetList = new ArrayList<>();
		
		for(Card2d card2d : topCards2d) {
			if(card2d.isNullCard()) {
				sourceList.add(null);
			}else {
				sourceList.add((Card) card2d.getCard());
			}
		}

		for(Card2d card2d : bottomCards2d) {
			if(card2d.isNullCard()) {
				targetList.add(null);
			}else {
				targetList.add((Card) card2d.getCard());
			}
		}
		
		msgUpdateList.setSourceList(sourceList);
		msgUpdateList.setTargetList(targetList);

		msgUpdateList.send();

	}

}
