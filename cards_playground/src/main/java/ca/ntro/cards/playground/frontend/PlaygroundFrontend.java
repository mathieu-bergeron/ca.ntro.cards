package ca.ntro.cards.playground.frontend;

import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.CommonFrontendRegistrar;
import ca.ntro.cards.playground.frontend.tasks.DisplayMainView;
import ca.ntro.cards.playground.frontend.tasks.InitialWindow;
import ca.ntro.cards.playground.frontend.views.PlaygroundRootView;
import ca.ntro.cards.playground.frontend.views.PlaygroundDashboardView;
import ca.ntro.cards.playground.frontend.views.PlaygroundMainView;

public class PlaygroundFrontend implements FrontendFx {

	@Override
	public void createTasks(FrontendTasks tasks) {
		InitialWindow.createTasks(tasks);
		DisplayMainView.createTasks(tasks);
	}

	@Override
	public void registerEvents(EventRegistrar registrar) {
		CommonFrontendRegistrar.registerEvents(registrar);
	}

	@Override
	public void registerViews(ViewRegistrarFx registrar) {

		registrar.registerStylesheet("/dev.css");
		//registrar.registerStylesheet("/prod.css");

		registrar.registerView(PlaygroundRootView.class, "/root.xml");
		registrar.registerView(PlaygroundMainView.class, "/main.xml");

		registrar.registerView(PlaygroundDashboardView.class, "/dashboard.xml");
		
		CommonFrontendRegistrar.registerViewData(registrar);
	}


	@Override
	public void execute() {
		
	}

}
