package ca.ntro.cards.foo.models.world2d;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.foo.FooConstants;
import ca.ntro.cards.foo.messages.MsgManualExecutionStep;
import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

public class FooProcedureWorld2d extends ProcedureWorld2d<FooProcedureObject2d, FooProcedureWorld2d, FooProcedureDrawingOptions> {
	
	private MsgManualExecutionStep msgManualModel = NtroApp.newMessage(MsgManualExecutionStep.class);
	
	@Override
	public void buildAndSendManualModel() {
		
		FooCardsModel manualModel = new FooCardsModel();
		
		msgManualModel.setManualModel(manualModel);
		msgManualModel.send();
	}


}
