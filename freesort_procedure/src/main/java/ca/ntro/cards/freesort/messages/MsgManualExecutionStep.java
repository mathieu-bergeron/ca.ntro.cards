package ca.ntro.cards.freesort.messages;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.freesort.models.TriLibre;

public class MsgManualExecutionStep extends MessageNtro {
	
	TriLibre manualModel;

	public TriLibre getManualModel() {
		return manualModel;
	}

	public void setManualModel(TriLibre manualModel) {
		this.manualModel = manualModel;
	}

	public void applyTo(TriLibre freesortModel) {
		freesortModel.copyDataFrom(manualModel);
	}
}
