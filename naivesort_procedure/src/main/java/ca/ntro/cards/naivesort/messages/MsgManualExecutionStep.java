package ca.ntro.cards.naivesort.messages;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.naivesort.models.NaivesortCardsModel;

public class MsgManualExecutionStep extends MessageNtro {
	
	NaivesortCardsModel manualModel;

	public NaivesortCardsModel getManualModel() {
		return manualModel;
	}

	public void setManualModel(NaivesortCardsModel manualModel) {
		this.manualModel = manualModel;
	}

	public void applyTo(NaivesortCardsModel naivesortModel) {
		naivesortModel.copyDataFrom(manualModel);
	}
}
