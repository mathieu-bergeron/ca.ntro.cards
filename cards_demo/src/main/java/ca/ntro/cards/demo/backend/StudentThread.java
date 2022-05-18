package ca.ntro.cards.demo.backend;

import ca.ntro.cards.demo.models.DemoCardsModel;

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
	}
	
	

}
