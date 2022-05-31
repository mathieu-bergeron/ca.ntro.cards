package ca.ntro.cards.foo.messages;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.foo.models.FooCardsModel;

public class MsgManualExecutionStep extends MessageNtro {
	
	FooCardsModel manualModel;

	public FooCardsModel getManualModel() {
		return manualModel;
	}

	public void setManualModel(FooCardsModel manualModel) {
		this.manualModel = manualModel;
	}

	public void applyTo(FooCardsModel fooModel) {
		fooModel.copyDataFrom(manualModel);
	}
}
