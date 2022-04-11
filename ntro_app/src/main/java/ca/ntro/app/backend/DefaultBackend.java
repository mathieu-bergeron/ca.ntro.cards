package ca.ntro.app.backend;

import ca.ntro.app.tasks.backend.BackendTasks;

public class DefaultBackend extends LocalBackendNtro {

	@Override
	public boolean isLocalBackend() {
		return true;
	}


	@Override
	public void createTasks(BackendTasks tasks) {
	}

	@Override
	public void execute() {
	}

}
