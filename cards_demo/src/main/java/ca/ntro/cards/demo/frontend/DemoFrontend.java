package ca.ntro.cards.demo.frontend;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.CommonFrontendRegistrar;
import ca.ntro.cards.demo.frontend.tasks.GameView;
import ca.ntro.cards.demo.frontend.tasks.Initialization;
import ca.ntro.cards.demo.frontend.tasks.Menu;
import ca.ntro.cards.demo.frontend.tasks.Navigation;
import ca.ntro.cards.demo.frontend.views.DemoRootView;
import ca.ntro.cards.demo.frontend.views.DemoDashboardView;
import ca.ntro.cards.demo.frontend.views.DemoGameView;
import ca.ntro.cards.demo.frontend.views.DemoMenuView;

public class DemoFrontend implements FrontendFx {

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

		registrar.registerView(DemoRootView.class, "/root.xml");

		registrar.registerView(DemoGameView.class, "/game.xml");
		registrar.registerView(DemoDashboardView.class, "/dashboard.xml");

		registrar.registerView(DemoMenuView.class, "/menu.xml");
		
		CommonFrontendRegistrar.registerViewData(registrar);
	}


	@Override
	public void execute() {
		
	}

}
