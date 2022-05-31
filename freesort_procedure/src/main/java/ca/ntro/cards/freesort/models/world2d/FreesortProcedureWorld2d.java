package ca.ntro.cards.freesort.models.world2d;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.freesort.FreesortConstants;
import ca.ntro.cards.freesort.messages.MsgManualExecutionStep;
import ca.ntro.cards.freesort.models.TriLibre;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

public class FreesortProcedureWorld2d extends ProcedureWorld2d<FreesortProcedureObject2d, FreesortProcedureWorld2d, FreesortProcedureDrawingOptions> {
	
	private MsgManualExecutionStep msgManualModel = NtroApp.newMessage(MsgManualExecutionStep.class);
	
	@Override
	public void buildAndSendManualModel() {

		List<FreesortCard2d> cards2d = new ArrayList<>();
		
		for(Object2d object2d : getObjects()) {

			if(object2d instanceof FreesortCard2d) {

				cards2d.add((FreesortCard2d) object2d);

			}
		}
		
		cards2d.sort((obj01, obj02) -> {
			return Double.compare(obj01.getTopLeftX(), obj02.getTopLeftX());
		});
		
		TriLibre manualModel = new TriLibre();
		
		Card[] cards = new Card[cards2d.size()];
		for(int i = 0; i < cards.length; i++) {
			cards[i] = (Card) cards2d.get(i).getCard();
		}

		manualModel.setCartes(cards);
		
		msgManualModel.setManualModel(manualModel);
		msgManualModel.send();
	}


}
