package ca.ntro.cards.playground.backend;

import ca.ntro.app.backend.LocalBackendNtro;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.playground.backend.tasks.ModifyGameModel;
import ca.ntro.cards.playground.backend.tasks.ModifySettingsModel;

public class PlaygroundBackend extends LocalBackendNtro {

	@Override
	public void createTasks(BackendTasks tasks) {
		ModifyGameModel.createTasks(tasks);
		ModifySettingsModel.createTasks(tasks);
	}

	@Override
	public void execute() {
		
	}

}
