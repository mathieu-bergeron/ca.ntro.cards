package ca.ntro.cards.playground.backend;

import ca.ntro.app.backend.LocalBackendNtro;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.playground.backend.tasks.ModifyModel;

public class PlaygroundBackend extends LocalBackendNtro {

	@Override
	public void createTasks(BackendTasks tasks) {
		
		ModifyModel.createTasks(tasks);

	}

	@Override
	public void execute() {
		
	}

}
