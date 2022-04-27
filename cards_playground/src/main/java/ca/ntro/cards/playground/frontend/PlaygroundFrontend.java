package ca.ntro.cards.playground.frontend;

import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.CommonFrontendRegistrar;
import ca.ntro.cards.playground.PlaygroundRegistrar;
import ca.ntro.cards.playground.frontend.tasks.InitialWindow;
import ca.ntro.cards.playground.frontend.views.PlaygroundRootView;
import ca.ntro.cards.playground.frontend.views.PlaygroundTabletopView;

public class PlaygroundFrontend implements FrontendFx {

	@Override
	public void createTasks(FrontendTasks tasks) {
		InitialWindow.createTasks(tasks);
	}

	@Override
	public void registerEvents(EventRegistrar registrar) {
		PlaygroundRegistrar.registerEvents(registrar);
	}

	@Override
	public void registerViews(ViewRegistrarFx registrar) {
		registrar.registerView(PlaygroundRootView.class, "/root.xml");
		registrar.registerView(PlaygroundTabletopView.class, "/tabletop.xml");
		
		CommonFrontendRegistrar.registerViewData(registrar);
	}


	@Override
	public void execute() {
		
	}

}
