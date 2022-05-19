package ca.ntro.cards.backend;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.messages.MsgExecutionEnded;
import ca.ntro.cards.models.CardsModel;

public class ModelThread<CARDS_MODEL extends CardsModel> extends Thread {
	
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
