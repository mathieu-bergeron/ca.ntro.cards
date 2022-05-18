package ca.ntro.cards.demo.backend;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.messages.MsgExecutionEnded;

public class StudentThread<STUDENT_MODEL extends DemoCardsModel> extends Thread {
	
	private STUDENT_MODEL model;

	public STUDENT_MODEL getModel() {
		return model;
	}

	public void setModel(STUDENT_MODEL model) {
		this.model = model;
	}
	
	@Override
	public void run() {
		model.sort();
		
		MsgExecutionEnded msgExecutionEnded = NtroApp.newMessage(MsgExecutionEnded.class);
		msgExecutionEnded.send();

	}
	
	

}
