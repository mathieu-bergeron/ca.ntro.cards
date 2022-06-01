package ca.ntro.cards.frontend;


import ca.ntro.app.frontend.ViewLoader;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.common.frontend.CommonFrontend;
import ca.ntro.cards.common.messages.MsgStopExecutionReplay;
import ca.ntro.cards.frontend.events.EvtStartExecutionReplay;
import ca.ntro.cards.frontend.events.MouseEvtOnPreviewCanvas;
import ca.ntro.cards.frontend.views.ProcedureSelectionsView;
import ca.ntro.cards.frontend.views.ProcedureCanvasView;
import ca.ntro.cards.frontend.views.ProcedureDashboardView;
import ca.ntro.cards.frontend.views.ProcedureMessagesView;
import ca.ntro.cards.frontend.views.ProcedureRootView;
import ca.ntro.cards.frontend.views.ProcedureSettingsView;
import ca.ntro.cards.frontend.views.ProcedureReplayView;
import ca.ntro.cards.frontend.views.ProcedureVariablesView;
import ca.ntro.cards.frontend.views.fragments.ProcedureMessageFragment;
import ca.ntro.cards.frontend.views.fragments.ProcedureTestCaseFragment;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.models.ProcedureSettingsModel;
import ca.ntro.core.reflection.observer.Modified;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.NtroApp;

public abstract class ProcedureFrontend<ROOT_VIEW            extends ProcedureRootView, 
                                        SETTINGS_VIEW        extends ProcedureSettingsView,
                                        CARDS_VIEW           extends ProcedureCanvasView, 
                                        DASHBOARD_VIEW       extends ProcedureDashboardView,
                                        SELECTIONS_VIEW      extends ProcedureSelectionsView,
                                        TEST_CASE_FRAGMENT   extends ProcedureTestCaseFragment,
                                        REPLAY_CONTROLS_VIEW extends ProcedureReplayView,
                                        VARIABLES_VIEW       extends ProcedureVariablesView,
                                        MESSAGES_VIEW        extends ProcedureMessagesView,
                                        MESSAGE_FRAGMENT     extends ProcedureMessageFragment,
                                        VIEW_DATA            extends ProcedureViewData,
                                        CARDS_MODEL          extends ProcedureCardsModel,
                                        DASHBOARD_MODEL      extends ProcedureDashboardModel,
                                        SETTINGS_MODEL       extends ProcedureSettingsModel>

       extends CommonFrontend<ROOT_VIEW,
                              SETTINGS_VIEW,
                              CARDS_VIEW,
                              DASHBOARD_VIEW,
                              MESSAGES_VIEW,
                              MESSAGE_FRAGMENT,
                              VIEW_DATA,
                              CARDS_MODEL,
                              DASHBOARD_MODEL,
                              SETTINGS_MODEL> {
                            	  
    protected Class<CARDS_MODEL> cardsModelClass(){
    	return getCanvasModelClass();
    }
    
    protected abstract Class<TEST_CASE_FRAGMENT> testCaseFragmentClass();

	@Override
	public void registerEvents(EventRegistrar registrar) {
		super.registerEvents(registrar);

		registrar.registerEvent(MouseEvtOnPreviewCanvas.class);

		registrar.registerEvent(EvtStartExecutionReplay.class);
	}

	@Override
	public void registerViews(ViewRegistrarFx registrar) {
		super.registerViews(registrar);

		registrar.registerView(selectionsViewClass(), "/selections.xml");
		registrar.registerView(replayControlsViewClass(), "/replay.xml");
		registrar.registerView(variablesViewClass(), "/variables.xml");
		registrar.registerView(testCaseFragmentClass(), "/fragments/test_case.xml");
	}

	@Override
	protected void addSubTasksToViewData(FrontendTasks tasks) {

		mouseEvtOnPreviewCanvas(tasks);

		stopCodeExecution(tasks);

		startCodeExecution(tasks);
	}
	

	private void startCodeExecution(FrontendTasks tasks) {
		tasks.task("startCodeExecution")

		      .waitsFor(event(EvtStartExecutionReplay.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  // FIXME: should be in the backend
		    	  NtroApp.models().suspendDiskOperations();

		    	  VIEW_DATA cardsViewData = inputs.get(created(viewDataClass()));
		    	  cardsViewData.startExecutionReplay();
		      });
	}


	private void stopCodeExecution(FrontendTasks tasks) {
		tasks.task("stopCodeExecution")

		      .waitsFor(message(MsgStopExecutionReplay.class))
		      
		      .thenExecutes(inputs -> {

		    	  // FIXME: should be in the backend
		    	  NtroApp.models().resumeDiskOperations();
		    	  
		    	  VIEW_DATA cardsViewData = inputs.get(created(viewDataClass()));
		    	  cardsViewData.stopExecutionReplay();

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

		tasks.task("refreshReplayControls")
		
			 .waitsFor(modified(getDashboardModelClass()))
		
		     .thenExecutes(inputs -> {
		    	 
		    	 REPLAY_CONTROLS_VIEW      replayView        = inputs.get(created(replayControlsViewClass()));
		    	 Modified<DASHBOARD_MODEL> modifiedDashboard = inputs.get(modified(getDashboardModelClass()));
		    	 
		    	 modifiedDashboard.currentValue().displayOn(replayView);

		     });

		tasks.task("displayTestCases")
		
			 .waitsFor(viewLoader(testCaseFragmentClass()))

			 .waitsFor(modified(getDashboardModelClass()))
		
		     .thenExecutes(inputs -> {
		    	 
		    	 SELECTIONS_VIEW                selectionsView         = inputs.get(created(selectionsViewClass()));
		    	 ViewLoader<TEST_CASE_FRAGMENT> testCaseFragmentLoader = inputs.get(viewLoader(testCaseFragmentClass()));
		    	 Modified<DASHBOARD_MODEL>      modifiedDashboard      = inputs.get(modified(getDashboardModelClass()));
		    	 
		    	 modifiedDashboard.currentValue().displayOn(selectionsView, 
		    			                                    modifiedDashboard.previousValue(),
		    			                                    testCaseFragmentLoader);

		     });

	}



	@Override
	protected void addDashboardSubViewLoaders(FrontendTasks subTasks) {

		subTasks.task(create(selectionsViewClass()))

		        .waitsFor(viewLoader(selectionsViewClass()))
		        
		        .thenExecutesAndReturnsValue(inputs -> {

		        	   ViewLoader<SELECTIONS_VIEW> selectionsViewLoader = inputs.get(viewLoader(selectionsViewClass()));
		        	   
		        	   return selectionsViewLoader.createView();
		        });

		subTasks.task(create(replayControlsViewClass()))

		        .waitsFor(viewLoader(replayControlsViewClass()))
		        
		        .thenExecutesAndReturnsValue(inputs -> {

		        	   ViewLoader<REPLAY_CONTROLS_VIEW> replayControlViewLoader = inputs.get(viewLoader(replayControlsViewClass()));
		        	   
		        	   return replayControlViewLoader.createView();
		        });

		subTasks.task(create(variablesViewClass()))

		        .waitsFor(viewLoader(variablesViewClass()))
		        
		        .thenExecutesAndReturnsValue(inputs -> {

		        	   ViewLoader<VARIABLES_VIEW> variablesViewLoader = inputs.get(viewLoader(variablesViewClass()));
		        	   
		        	   return variablesViewLoader.createView();
		        });
	}

	@Override
	protected void installDashboardSubViews(SimpleTaskCreator<?> taskCreator) {
		
		taskCreator.waitsFor(created(selectionsViewClass()))
		           .waitsFor(created(replayControlsViewClass()))
		           .waitsFor(created(variablesViewClass()))
		           
		           .thenExecutes(inputs -> {
		        	   
		        	   SELECTIONS_VIEW      selectionsView     = inputs.get(created(selectionsViewClass()));
		        	   REPLAY_CONTROLS_VIEW replayControlsView = inputs.get(created(replayControlsViewClass()));
		        	   VARIABLES_VIEW       variablesView      = inputs.get(created(variablesViewClass()));
		        	   DASHBOARD_VIEW       dashboardView      = inputs.get(created(dashboardViewClass()));
		        	   
		        	   dashboardView.installSelectionsView(selectionsView);
		        	   dashboardView.installReplayControlsView(replayControlsView);
		        	   dashboardView.installVariablesView(variablesView);
		           });
	}

	protected abstract Class<SELECTIONS_VIEW> selectionsViewClass();
	protected abstract Class<REPLAY_CONTROLS_VIEW> replayControlsViewClass();
	protected abstract Class<VARIABLES_VIEW> variablesViewClass();
	
	
	@Override
	protected void createAdditionnalTasks(FrontendTasks tasks) {
		
		tasks.taskGroup("Cards")
		
		     .waitsFor("Initialization")
		     
		     .andContains(subTasks -> {
		    	 
		    	 displayCardsModel(subTasks);
		    	 
		    	 addSubTasksToCards(subTasks);

		     });
	}

	protected abstract void addSubTasksToCards(FrontendTasks subTasks);

	private void displayCardsModel(FrontendTasks tasks) {
		
		tasks.task("displayCardsModel")
		
		     .waitsFor(modified(cardsModelClass()))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 VARIABLES_VIEW         variablesView      = inputs.get(created(variablesViewClass()));
		    	 Modified<CARDS_MODEL>  modifiedCardsModel = inputs.get(modified(cardsModelClass()));
		    	 
		    	 modifiedCardsModel.currentValue().displayOn(variablesView);
		     });
	}

	

	
}
