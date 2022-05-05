package ca.ntro.cards.demo.backend;

import ca.ntro.app.backend.LocalBackendNtro;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.backend.tasks.ModifyGameModel;

public class DemoBackend extends LocalBackendNtro {

	@Override
	public void createTasks(BackendTasks tasks) {
		
		ModifyGameModel.createTasks(tasks);

	}

	@Override
	public void execute() {
		
	}

}
