package ca.ntro.cards.playground.frontend;

import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.CommonRegistrar;
import ca.ntro.cards.playground.frontend.views.PlaygroundRootView;
import ca.ntro.cards.playground.frontend.views.PlaygroundTableTopView;

public class PlaygroundFrontend implements FrontendFx {

	@Override
	public void registerEvents(EventRegistrar registrar) {
		CommonRegistrar.registerEvents(registrar);

	}

	@Override
	public void registerViews(ViewRegistrarFx registrar) {
		
		registrar.registerView(PlaygroundRootView.class, "/root.xml");
		registrar.registerView(PlaygroundTableTopView.class, "/table_top.xml");
	}

	@Override
	public void createTasks(FrontendTasks tasks) {
		
	}

	@Override
	public void execute() {
		
	}

}
