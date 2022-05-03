package ca.ntro.cards.playground.frontend;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.CommonFrontendRegistrar;
import ca.ntro.cards.playground.frontend.tasks.GameView;
import ca.ntro.cards.playground.frontend.tasks.Initialization;
import ca.ntro.cards.playground.frontend.tasks.Menu;
import ca.ntro.cards.playground.frontend.tasks.Navigation;
import ca.ntro.cards.playground.frontend.views.PlaygroundRootView;
import ca.ntro.cards.playground.frontend.views.PlaygroundDashboardView;
import ca.ntro.cards.playground.frontend.views.PlaygroundGameView;
import ca.ntro.cards.playground.frontend.views.PlaygroundMenuView;

public class PlaygroundFrontend implements FrontendFx {

	@Override
	public void createTasks(FrontendTasks tasks) {
		Initialization.createTasks(tasks);
		GameView.createTasks(tasks);
		Navigation.createTasks(tasks);
		Menu.createTasks(tasks);
	}

	@Override
	public void registerEvents(EventRegistrar registrar) {
		CommonFrontendRegistrar.registerEvents(registrar);
	}

	@Override
	public void registerViews(ViewRegistrarFx registrar) {
		registrar.registerDefaultResources("/strings_fr.properties");
		registrar.registerResources(NtroApp.locale("en"), "/strings_en.properties");

		registrar.registerStylesheet("/dev.css");
		//registrar.registerStylesheet("/prod.css");

		registrar.registerView(PlaygroundRootView.class, "/root.xml");

		registrar.registerView(PlaygroundGameView.class, "/game.xml");
		registrar.registerView(PlaygroundDashboardView.class, "/dashboard.xml");

		registrar.registerView(PlaygroundMenuView.class, "/menu.xml");
		
		CommonFrontendRegistrar.registerViewData(registrar);
	}


	@Override
	public void execute() {
		
	}

}
