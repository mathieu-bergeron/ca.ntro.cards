package ca.ntro.app.frontend;

import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;

public interface Frontend<VR extends ViewRegistrar> {

	void registerEvents(EventRegistrar registrar);

	void registerViews(VR registrar);

	void createTasks(FrontendTasks tasks);

	void execute();

}
