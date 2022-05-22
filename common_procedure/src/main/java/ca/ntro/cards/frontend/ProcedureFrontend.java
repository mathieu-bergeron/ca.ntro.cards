package ca.ntro.cards.frontend;

import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.common.frontend.CommonFrontend;
import ca.ntro.cards.common.messages.MsgExecutionEnded;
import ca.ntro.cards.frontend.events.EvtStartCodeExecution;
import ca.ntro.cards.frontend.events.EvtStopCodeExecution;
import ca.ntro.cards.frontend.events.MouseEvtOnPreviewCanvas;
import ca.ntro.cards.frontend.views.CategoriesView;
import ca.ntro.cards.frontend.views.ProcedureCanvasView;
import ca.ntro.cards.frontend.views.ProcedureDashboardView;
import ca.ntro.cards.frontend.views.ProcedureRootView;
import ca.ntro.cards.frontend.views.ProcedureSettingsView;
import ca.ntro.cards.frontend.views.ReplayControlsView;
import ca.ntro.cards.frontend.views.VariablesView;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.models.ProcedureSettingsModel;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

public abstract class ProcedureFrontend<ROOT_VIEW            extends ProcedureRootView, 
                                        SETTINGS_VIEW        extends ProcedureSettingsView,
                                        CARDS_VIEW           extends ProcedureCanvasView, 
                                        DASHBOARD_VIEW       extends ProcedureDashboardView,
                                        CATEGORIES_VIEW      extends CategoriesView,
                                        REPLAY_CONTROLS_VIEW extends ReplayControlsView,
                                        VARIABLES_VIEW       extends VariablesView,
                                        VIEW_DATA            extends ProcedureViewData,
                                        CARDS_MODEL          extends ProcedureCardsModel,
                                        DASHBOARD_MODEL      extends ProcedureDashboardModel,
                                        SETTINGS_MODEL       extends ProcedureSettingsModel>

       extends CommonFrontend<ROOT_VIEW,
                              SETTINGS_VIEW,
                              CARDS_VIEW,
                              DASHBOARD_VIEW,
                              VIEW_DATA,
                              CARDS_MODEL,
                              DASHBOARD_MODEL,
                              SETTINGS_MODEL> {

	@Override
	public void registerEvents(EventRegistrar registrar) {
		super.registerEvents(registrar);

		registrar.registerEvent(MouseEvtOnPreviewCanvas.class);

		registrar.registerEvent(EvtStartCodeExecution.class);
		registrar.registerEvent(EvtStopCodeExecution.class);
	}

	@Override
	public void registerViews(ViewRegistrarFx registrar) {
		super.registerViews(registrar);

		registrar.registerView(categoriesViewClass(), "/categories.xml");
		registrar.registerView(replayControlsViewClass(), "/replay_controls.xml");
		registrar.registerView(variablesViewClass(), "/variables.xml");
	}


	@Override
	protected void addSubTasksToCards(FrontendTasks tasks) {

		mouseEvtOnPreviewCanvas(tasks);

		executionEnded(tasks);

		stopCodeExecution(tasks);

		startCodeExecution(tasks);
	}
	
	
	
	

	private void startCodeExecution(FrontendTasks tasks) {
		tasks.task("startCodeExecution")

		      .waitsFor(event(EvtStartCodeExecution.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  VIEW_DATA cardsViewData = inputs.get(created(viewDataClass()));
		    	  cardsViewData.startCodeExecution();
		      });
	}


	private void stopCodeExecution(FrontendTasks tasks) {
		tasks.task("stopCodeExecution")

		      .waitsFor(event(EvtStopCodeExecution.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  VIEW_DATA cardsViewData = inputs.get(created(viewDataClass()));
		    	  cardsViewData.stopCodeExecution();

		      });
	}


	private void executionEnded(FrontendTasks tasks) {
		tasks.task("executionEnded")

		      .waitsFor(message(MsgExecutionEnded.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  VIEW_DATA cardsViewData = inputs.get(created(viewDataClass()));
		    	  cardsViewData.stopCodeExecution();

		      });
	}


	private void mouseEvtOnPreviewCanvas(FrontendTasks tasks) {
		tasks.task("mouseEvtOnPreviewCanvas")
		
		      .waitsFor(event(MouseEvtOnPreviewCanvas.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  MouseEvtOnPreviewCanvas mouseEventOnTabletop = inputs.get(event(MouseEvtOnPreviewCanvas.class));
		    	  CARDS_VIEW         cardsView            = inputs.get(created(canvasViewClass()));
		    	  
		    	  mouseEventOnTabletop.applyTo(cardsView);
		      });
	}

	@Override
	protected void addSubTasksToDashboard(FrontendTasks tasks) {

		executionEndedDashboard(tasks);

	}


	private void executionEndedDashboard(FrontendTasks tasks) {
		tasks.task("executionEndedDashboard")
		
			 .waitsFor(message(MsgExecutionEnded.class))
		
		     .thenExecutes(inputs -> {
		    	 
		    	 DASHBOARD_VIEW dashboardView  = inputs.get(created(dashboardViewClass()));
		    	 
		    	 //dashboardView.disableExecutionButtons();

		     });
	}

	@Override
	protected void installDashboardSubViews(SimpleTaskCreator<?> taskCreator) {
		
		taskCreator.waitsFor(viewLoader(categoriesViewClass()))
		           .waitsFor(viewLoader(replayControlsViewClass()))
		           .waitsFor(viewLoader(variablesViewClass()))
		           
		           .thenExecutes(inputs -> {
		        	   
		        	   ViewLoader<CATEGORIES_VIEW>      categoriesViewLoader     = inputs.get(viewLoader(categoriesViewClass()));
		        	   ViewLoader<REPLAY_CONTROLS_VIEW> replayControlsViewLoader = inputs.get(viewLoader(replayControlsViewClass()));
		        	   ViewLoader<VARIABLES_VIEW>       variablesViewLoader      = inputs.get(viewLoader(variablesViewClass()));
		        	   DASHBOARD_VIEW                   dashboardView            = inputs.get(created(dashboardViewClass()));
		        	   
		        	   dashboardView.installCategoriesView(categoriesViewLoader.createView());
		        	   dashboardView.installReplayControlsView(replayControlsViewLoader.createView());
		        	   dashboardView.installVariablesView(variablesViewLoader.createView());
		        	   
		           });
	}

	protected abstract Class<CATEGORIES_VIEW> categoriesViewClass();
	protected abstract Class<REPLAY_CONTROLS_VIEW> replayControlsViewClass();
	protected abstract Class<VARIABLES_VIEW> variablesViewClass();

	
}
