package ca.ntro.cards.frontend;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtHideMenu;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import ca.ntro.cards.frontend.events.EvtQuit;
import ca.ntro.cards.frontend.events.EvtResizeViewport;
import ca.ntro.cards.frontend.events.EvtShowMenu;
import ca.ntro.cards.frontend.events.MouseEvtOnTabletop;
import ca.ntro.cards.frontend.events.MouseEvtOnViewer;
import ca.ntro.cards.frontend.tasks.Cards;
import ca.ntro.cards.frontend.tasks.Initialization;
import ca.ntro.cards.frontend.tasks.Menu;
import ca.ntro.cards.frontend.tasks.Navigation;
import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.cards.frontend.views.CardsView;
import ca.ntro.cards.frontend.views.SettingsView;
import ca.ntro.cards.frontend.views.RootView;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.SettingsModel;

public abstract class CommonFrontend<ROOT_VIEW extends RootView, 
                                     SETTINGS_VIEW extends SettingsView,
                                     CARDS_VIEW extends CardsView, 
                                     DASHBOARD_VIEW extends DashboardView,
                                     CARDS_VIEW_DATA extends CardsViewData,
                                     SETTINGS_MODEL extends SettingsModel,
                                     CARDS_MODEL extends CardsModel> 

       implements FrontendFx {

	@Override
	public void registerEvents(EventRegistrar registrar) {
		registrar.registerEvent(EvtMoveViewport.class);
		registrar.registerEvent(EvtResizeViewport.class);
		registrar.registerEvent(MouseEvtOnViewer.class);
		registrar.registerEvent(MouseEvtOnTabletop.class);

		registrar.registerEvent(EvtShowMenu.class);
		registrar.registerEvent(EvtHideMenu.class);
		registrar.registerEvent(EvtQuit.class);

		registerAdditionnalEvents(registrar);
	}

	protected abstract void registerAdditionnalEvents(EventRegistrar registrar);

	protected abstract boolean isProd();
	
	protected abstract Class<ROOT_VIEW> rootViewClass();
	protected abstract Class<SETTINGS_VIEW> settingsViewClass();
	protected abstract Class<CARDS_VIEW> cardsViewClass();
	protected abstract Class<DASHBOARD_VIEW> dashboardViewClass();

	@Override
	public void registerViews(ViewRegistrarFx registrar) {
		registrar.registerDefaultResources("/strings_fr.properties");
		registrar.registerResources(NtroApp.locale("en"), "/strings_en.properties");
		
		if(isProd()) {
			registrar.registerStylesheet("/dev.css");
		}else {
		    registrar.registerStylesheet("/prod.css");
		}

		registrar.registerView(rootViewClass(), "/root.xml");
		registrar.registerView(settingsViewClass(), "/settings.xml");
		registrar.registerView(cardsViewClass(), "/cards.xml");
		registrar.registerView(dashboardViewClass(), "/dashboard.xml");
		
		registerAdditionnalViews(registrar);
	}

	protected abstract void registerAdditionnalViews(ViewRegistrarFx registrar);
	
	protected abstract Class<CARDS_VIEW_DATA> cardsViewDataClass();
	
	protected abstract Class<SETTINGS_MODEL> settingsModelClass();

	protected abstract Class<CARDS_MODEL> cardsModelClass();

	@Override
	public void createTasks(FrontendTasks tasks) {
		Initialization.createTasks(tasks,
				                   rootViewClass(),
				                   cardsViewClass(),
				                   settingsViewClass(),
				                   dashboardViewClass());

		Cards.createTasks(tasks, 
				          cardsViewClass(),
				          cardsViewDataClass(),
				          cardsModelClass(),
				          settingsModelClass(),
				          dashboardViewClass());

		Navigation.createTasks(tasks, 
				               rootViewClass());
		
		Menu.createTasks(tasks, 
				         settingsViewClass(), 
				         settingsModelClass());
		
	}
	
	
	
	
	
	
	
	@Override
	public void execute() {
	}

}
