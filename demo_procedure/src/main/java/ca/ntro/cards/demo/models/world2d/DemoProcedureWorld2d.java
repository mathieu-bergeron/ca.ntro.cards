package ca.ntro.cards.demo.models.world2d;


import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

public class DemoProcedureWorld2d extends ProcedureWorld2d<DemoProcedureObject2d, DemoProcedureWorld2d, DemoProcedureDrawingOptions> {
	
	private MsgUpdateList msgUpdateList = NtroApp.newMessage(MsgUpdateList.class);

	@SuppressWarnings("rawtypes")
	@Override
	protected void forgetDraggedCard() {
		super.forgetDraggedCard();
		
		List<DemoCard2d> topCards2d = new ArrayList<>();
		List<DemoCard2d> bottomCards2d = new ArrayList<>();
		
		for(Object2d object2d : getObjects()) {

			if(object2d instanceof DemoCard2d) {

				if(object2d.topLeftY() < CommonConstants.INITIAL_CARD_HEIGHT_MILIMETERS) {
					
					topCards2d.add((DemoCard2d) object2d);

				}else {

					bottomCards2d.add((DemoCard2d) object2d);
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

		for(DemoCard2d card2d : bottomCards2d) {
			if(card2d.isNullCard()) {
				sourceList.add(null);
			}else {
				sourceList.add((Card) card2d.getCard());
			}
		}
		
		for(DemoCard2d card2d : topCards2d) {
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
