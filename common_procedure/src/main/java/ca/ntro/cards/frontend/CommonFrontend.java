package ca.ntro.cards.frontend;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtStartCodeExecution;
import ca.ntro.cards.frontend.events.EvtStopCodeExecution;
import ca.ntro.cards.frontend.tasks.Cards;
import ca.ntro.cards.frontend.tasks.Dashboard;
import ca.ntro.cards.frontend.tasks.Initialization;
import ca.ntro.cards.frontend.tasks.Settings;
import ca.ntro.cards.frontend.views.data.ExploreViewData;
import ca.ntro.cards.frontend.tasks.Navigation;
import ca.ntro.cards.models.ExploreCardsModel;
import ca.ntro.cards.models.DashboardModel;
import ca.ntro.cards.models.SettingsModel;
import common.frontend.events.EvtHideMenu;
import common.frontend.events.EvtMoveViewport;
import common.frontend.events.EvtQuit;
import common.frontend.events.EvtResizeViewport;
import common.frontend.events.EvtShowMenu;
import common.frontend.events.MouseEvtOnTabletop;
import common.frontend.events.MouseEvtOnViewer;
import common.frontend.views.CardsView;
import common.frontend.views.DashboardView;
import common.frontend.views.RootView;
import common.frontend.views.SettingsView;
import common.frontend.views.data.CardsViewData;

public abstract class CommonFrontend<ROOT_VIEW extends RootView, 
                                     SETTINGS_VIEW extends SettingsView,
                                     CARDS_VIEW extends CardsView, 
                                     DASHBOARD_VIEW extends DashboardView,
                                     CARDS_VIEW_DATA extends ExploreViewData,
                                     CARDS_MODEL extends ExploreCardsModel,
                                     DASHBOARD_MODEL extends DashboardModel,
                                     SETTINGS_MODEL extends SettingsModel>

                 implements FrontendFx {

	private Class<CARDS_MODEL> cardsModelClass;
	private Class<DASHBOARD_MODEL> dashboardModelClass;
	private Class<SETTINGS_MODEL> settingsModelClass;

	public Class<CARDS_MODEL> getCardsModelClass() {
		return cardsModelClass;
	}

	public void setCardsModelClass(Class<CARDS_MODEL> cardsModelClass) {
		this.cardsModelClass = cardsModelClass;
	}

	public Class<DASHBOARD_MODEL> getDashboardModelClass() {
		return dashboardModelClass;
	}

	public void setDashboardModelClass(Class<DASHBOARD_MODEL> dashboardModelClass) {
		this.dashboardModelClass = dashboardModelClass;
	}

	public Class<SETTINGS_MODEL> getSettingsModelClass() {
		return settingsModelClass;
	}

	public void setSettingsModelClass(Class<SETTINGS_MODEL> settingsModelClass) {
		this.settingsModelClass = settingsModelClass;
	}

	@Override
	public void registerEvents(EventRegistrar registrar) {
		registrar.registerEvent(EvtMoveViewport.class);
		registrar.registerEvent(EvtResizeViewport.class);
		registrar.registerEvent(MouseEvtOnViewer.class);
		registrar.registerEvent(MouseEvtOnTabletop.class);

		registrar.registerEvent(EvtShowMenu.class);
		registrar.registerEvent(EvtHideMenu.class);
		registrar.registerEvent(EvtQuit.class);
		
		registrar.registerEvent(EvtStartCodeExecution.class);
		registrar.registerEvent(EvtStopCodeExecution.class);

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

	@Override
	public void createTasks(FrontendTasks tasks) {

		Initialization.createTasks(tasks,
				                   rootViewClass(),
				                   cardsViewClass(),
				                   settingsViewClass(),
				                   dashboardViewClass(),
				                   subTasks -> {

				                	   addSubTasksToInitialization(subTasks);
				                	   
				                   });

		Cards.createTasks(tasks, 
				          cardsViewClass(),
				          cardsViewDataClass(),
				          cardsModelClass,
				          settingsModelClass,
				          dashboardViewClass(),
				          subTasks -> {
				        	  
				        	  addSubTasksToCards(subTasks);
				        	  
				          });

		Navigation.createTasks(tasks, 
				               rootViewClass(),
				               subTasks -> {
				            	   
				            	   addSubTasksToNavigation(subTasks);
				            	   
				               });

		Dashboard.createTasks(tasks, 
				              dashboardViewClass(),
				              dashboardModelClass,

				             subTasks -> {
				            	 
				            	 addSubTasksToDashboard(subTasks);
				            	 
				             });
		
		Settings.createTasks(tasks, 
				             settingsViewClass(), 
				             settingsModelClass,
				             subTasks -> {
				            	 
				            	 addSubTasksToSettings(subTasks);
				            	 
				             });
		
		createAdditionnalTasks(tasks);

	}
	
	protected abstract void addSubTasksToInitialization(FrontendTasks subTasks);

	protected abstract void addSubTasksToCards(FrontendTasks subTasks);

	protected abstract void addSubTasksToNavigation(FrontendTasks subTasks);

	protected abstract void addSubTasksToSettings(FrontendTasks subTasks);

	protected abstract void addSubTasksToDashboard(FrontendTasks subTasks);

	protected abstract void createAdditionnalTasks(FrontendTasks tasks);

	

	@Override
	public void execute() {
	}

}
