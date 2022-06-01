package ca.ntro.cards.common.frontend;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.common.frontend.events.EvtHideMenu;
import ca.ntro.cards.common.frontend.events.EvtHideMessages;
import ca.ntro.cards.common.frontend.events.EvtMoveViewport;
import ca.ntro.cards.common.frontend.events.EvtQuit;
import ca.ntro.cards.common.frontend.events.EvtResizeViewport;
import ca.ntro.cards.common.frontend.events.EvtShowMenu;
import ca.ntro.cards.common.frontend.events.EvtShowMessages;
import ca.ntro.cards.common.frontend.events.MouseEvtOnMainCanvas;
import ca.ntro.cards.common.frontend.tasks.ViewData;
import ca.ntro.cards.common.frontend.tasks.Dashboard;
import ca.ntro.cards.common.frontend.tasks.Initialization;
import ca.ntro.cards.common.frontend.tasks.Navigation;
import ca.ntro.cards.common.frontend.tasks.Settings;
import ca.ntro.cards.common.frontend.views.CommonCanvasView;
import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import ca.ntro.cards.common.frontend.views.CommonMessagesView;
import ca.ntro.cards.common.frontend.views.CommonRootView;
import ca.ntro.cards.common.frontend.views.CommonSettingsView;
import ca.ntro.cards.common.frontend.views.fragments.CommonMessageFragment;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonSettingsModel;

public abstract class CommonFrontend<ROOT_VIEW        extends CommonRootView, 
                                     SETTINGS_VIEW    extends CommonSettingsView,
                                     CANVAS_VIEW      extends CommonCanvasView, 
                                     DASHBOARD_VIEW   extends CommonDashboardView,
                                     MESSAGES_VIEW    extends CommonMessagesView,
                                     MESSAGE_FRAGMENT extends CommonMessageFragment,
                                     VIEW_DATA        extends CommonViewData,
                                     CANVAS_MODEL     extends CommonCanvasModel,
                                     DASHBOARD_MODEL  extends CommonDashboardModel,
                                     SETTINGS_MODEL   extends CommonSettingsModel>

                 implements FrontendFx {

	private Class<CANVAS_MODEL>    canvasModelClass;
	private Class<DASHBOARD_MODEL> dashboardModelClass;
	private Class<SETTINGS_MODEL>  settingsModelClass;

	public Class<CANVAS_MODEL> getCanvasModelClass() {
		return canvasModelClass;
	}

	public void setCanvasModelClass(Class<CANVAS_MODEL> canvasModelClass) {
		this.canvasModelClass = canvasModelClass;
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
		registrar.registerEvent(MouseEvtOnMainCanvas.class);

		registrar.registerEvent(EvtShowMenu.class);
		registrar.registerEvent(EvtHideMenu.class);
		registrar.registerEvent(EvtShowMessages.class);
		registrar.registerEvent(EvtHideMessages.class);
		registrar.registerEvent(EvtQuit.class);
		

		registerAdditionnalEvents(registrar);
	}

	protected abstract void registerAdditionnalEvents(EventRegistrar registrar);

	protected abstract boolean isProd();
	
	protected abstract Class<ROOT_VIEW>      rootViewClass();
	protected abstract Class<SETTINGS_VIEW>  settingsViewClass();
	protected abstract Class<CANVAS_VIEW>    canvasViewClass();
	protected abstract Class<DASHBOARD_VIEW> dashboardViewClass();
	protected abstract Class<MESSAGES_VIEW>   messagesViewClass();
	protected abstract Class<MESSAGE_FRAGMENT> messageFragmentClass();

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
		registrar.registerView(canvasViewClass(), "/canvas.xml");
		registrar.registerView(dashboardViewClass(), "/dashboard.xml");
		registrar.registerView(messagesViewClass(), "/messages.xml");
		registrar.registerView(messageFragmentClass(), "/fragments/message.xml");

		registerAdditionnalViews(registrar);
	}

	protected abstract void registerAdditionnalViews(ViewRegistrarFx registrar);
	
	protected abstract Class<VIEW_DATA> viewDataClass();

	@Override
	public void createTasks(FrontendTasks tasks) {

		Initialization.createTasks(tasks,
				                   rootViewClass(),
				                   canvasViewClass(),
				                   settingsViewClass(),
				                   messagesViewClass(),
				                   dashboardViewClass(),
				                   subTasks -> {
				                	   
				                	   addDashboardSubViewLoaders(subTasks);
				                	   
				                   },
				                   taskCreator -> {
				                	   
				                	   installDashboardSubViews(taskCreator);
				                	   
				                   },
				                   subTasks -> {

				                	   addSubTasksToInitialization(subTasks);
				                	   
				                   });

		ViewData.createTasks(tasks, 
				             canvasViewClass(),
				             viewDataClass(),
				             canvasModelClass,
				             settingsModelClass,
				             dashboardViewClass(),
				             subTasks -> {
				        	  
								  addSubTasksToViewData(subTasks);
				        	  
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

	protected abstract void addDashboardSubViewLoaders(FrontendTasks subTasks);

	protected abstract void installDashboardSubViews(SimpleTaskCreator<?> taskCreator);

	protected abstract void addSubTasksToInitialization(FrontendTasks subTasks);

	protected abstract void addSubTasksToViewData(FrontendTasks subTasks);

	protected abstract void addSubTasksToNavigation(FrontendTasks subTasks);

	protected abstract void addSubTasksToSettings(FrontendTasks subTasks);

	protected abstract void addSubTasksToDashboard(FrontendTasks subTasks);

	protected abstract void createAdditionnalTasks(FrontendTasks tasks);


	@Override
	public void execute() {
	}

}
