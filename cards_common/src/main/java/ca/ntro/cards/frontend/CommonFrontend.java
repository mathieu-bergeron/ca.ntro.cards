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
import ca.ntro.cards.frontend.tasks.Dashboard;
import ca.ntro.cards.frontend.tasks.Initialization;
import ca.ntro.cards.frontend.tasks.Settings;
import ca.ntro.cards.frontend.tasks.Navigation;
import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.cards.frontend.views.CardsView;
import ca.ntro.cards.frontend.views.SettingsView;
import ca.ntro.cards.frontend.views.RootView;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;
import ca.ntro.cards.models.SettingsModel;

public abstract class CommonFrontend<ROOT_VIEW extends RootView, 
                                     SETTINGS_VIEW extends SettingsView,
                                     CARDS_VIEW extends CardsView, 
                                     DASHBOARD_VIEW extends DashboardView,
                                     CARDS_VIEW_DATA extends CardsViewData,
                                     CARDS_MODEL extends CardsModel,
                                     DASHBOARD_MODEL extends DashboardModel,
                                     SETTINGS_MODEL extends SettingsModel,
                                     MSG_REGISTER_SIMPLE_OPERATION extends MsgRegisterSimpleOperation<CARDS_MODEL, 
                                                                                                      DASHBOARD_MODEL>> 
                 implements FrontendFx {

	private Class<CARDS_MODEL> cardsModelClass;
	private Class<DASHBOARD_MODEL> dashboardModelClass;
	private Class<SETTINGS_MODEL> settingsModelClass;
	private Class<? extends MsgRegisterSimpleOperation> msgRegisterSimpleOperationClass;

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

	public Class<? extends MsgRegisterSimpleOperation> getMsgRegisterSimpleOperationClass() {
		return msgRegisterSimpleOperationClass;
	}

	public void setMsgRegisterSimpleOperationClass(Class<? extends MsgRegisterSimpleOperation> msgRegisterSimpleOperationClass) {
		this.msgRegisterSimpleOperationClass = msgRegisterSimpleOperationClass;
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
