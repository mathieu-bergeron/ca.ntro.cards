package ca.ntro.cards.common.backend;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.common.messages.MsgExecutionEnded;
import ca.ntro.cards.common.models.CommonCardsModel;

public class CardsModelThread<CARDS_MODEL extends CommonCardsModel> extends Thread {
	
	private CARDS_MODEL model;

	public CARDS_MODEL getModel() {
		return model;
	}

	public void setModel(CARDS_MODEL model) {
		this.model = model;
	}
	
	@Override
	public void run() {
		model.run();

		MsgExecutionEnded msgExecutionEnded = NtroApp.newMessage(MsgExecutionEnded.class);
		msgExecutionEnded.send();
	}

}
