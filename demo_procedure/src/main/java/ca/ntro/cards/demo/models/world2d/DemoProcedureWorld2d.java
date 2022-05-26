package ca.ntro.cards.demo.models.world2d;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.demo.DemoConstants;
import ca.ntro.cards.demo.messages.MsgManualExecutionStep;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;
import ca.ntro.core.initialization.Ntro;

public class DemoProcedureWorld2d extends ProcedureWorld2d<DemoProcedureObject2d, DemoProcedureWorld2d, DemoProcedureDrawingOptions> {
	
	private MsgManualExecutionStep msgUpdateList = NtroApp.newMessage(MsgManualExecutionStep.class);

	@Override
	public void buildAndSendManualModel() {

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

		//double markerTopLeftX = 10 + cardWidth + cardWidth / 2 + getIndicePlusPetit() * cardWidth * 3 / 2;
		double cardWidth = DemoConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = DemoConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

		Object2d marker2d = objectById("smallestElement");
		int indexSmallest = (int) Math.round((marker2d.topLeftX() - 10 - cardWidth - cardWidth / 2) * 2 / 3 / cardWidth);
		
		DemoCardsModel manualModel = new DemoCardsModel();
		
	
		Card[] source = new Card[sourceList.size()];
		for(int i = 0; i < sourceList.size(); i++) {
			source[i] = sourceList.get(i);
		}

		Card[] target = new Card[targetList.size()];
		for(int i = 0; i < targetList.size(); i++) {
			target[i] = targetList.get(i);
		}
		
		manualModel.setSource(source);
		manualModel.setCible(target);
		manualModel.setIndicePlusPetit(indexSmallest);
		
		msgUpdateList.setManualModel(manualModel);
		msgUpdateList.send();
	}


}
