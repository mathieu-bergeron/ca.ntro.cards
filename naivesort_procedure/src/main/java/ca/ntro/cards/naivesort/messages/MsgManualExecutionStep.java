package ca.ntro.cards.naivesort.messages;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.naivesort.models.TriNaif;

public class MsgManualExecutionStep extends MessageNtro {
	
	TriNaif manualModel;

	public TriNaif getManualModel() {
		return manualModel;
	}

	public void setManualModel(TriNaif manualModel) {
		this.manualModel = manualModel;
	}

	public void applyTo(TriNaif naivesortModel) {
		naivesortModel.copyDataFrom(manualModel);
	}
}
