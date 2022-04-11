package ca.ntro.app.backend;

import ca.ntro.app.tasks.backend.BackendTasks;

public interface LocalBackend extends Backend {

	void createTasks(BackendTasks tasks);

	void execute();

}
