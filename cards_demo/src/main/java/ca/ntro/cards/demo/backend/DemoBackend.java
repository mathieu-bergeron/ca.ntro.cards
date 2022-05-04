package ca.ntro.cards.demo.backend;

import ca.ntro.app.backend.LocalBackendNtro;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.backend.tasks.ModifyModel;

public class DemoBackend extends LocalBackendNtro {

	@Override
	public void createTasks(BackendTasks tasks) {
		
		ModifyModel.createTasks(tasks);

	}

	@Override
	public void execute() {
		
	}

}
