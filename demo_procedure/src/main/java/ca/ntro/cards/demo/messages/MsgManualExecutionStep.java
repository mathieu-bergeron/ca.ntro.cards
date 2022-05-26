package ca.ntro.cards.demo.messages;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.demo.models.DemoCardsModel;

public class MsgManualExecutionStep extends MessageNtro {
	
	DemoCardsModel manualModel;

	public DemoCardsModel getManualModel() {
		return manualModel;
	}

	public void setManualModel(DemoCardsModel manualModel) {
		this.manualModel = manualModel;
	}

	public void applyTo(DemoCardsModel demoModel) {
		demoModel.copyDataFrom(manualModel);
	}
}
