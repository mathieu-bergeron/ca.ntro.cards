package ca.ntro.cards.common.backend;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.common.messages.MsgExecutionEnded;
import ca.ntro.cards.common.models.CommonExecutableModel;

public class ExecutableModelThread<CARDS_MODEL extends CommonExecutableModel> extends Thread {
	
	private CARDS_MODEL model;

	public CARDS_MODEL getModel() {
		return model;
	}

	public void setModel(CARDS_MODEL model) {
		this.model = model;
	}
	
	@Override
	public void run() {
		model.onBeforeRunning();
		model.run();
		model.onAfterRunning();

		MsgExecutionEnded msgExecutionEnded = NtroApp.newMessage(MsgExecutionEnded.class);
		msgExecutionEnded.send();
	}

}