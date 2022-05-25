package ca.ntro.cards.common.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.common.frontend.events.EvtMoveViewport;
import ca.ntro.cards.common.frontend.events.EvtResizeViewport;
import ca.ntro.cards.common.frontend.events.MouseEvtOnMainCanvas;
import ca.ntro.cards.common.frontend.views.CommonCanvasView;
import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonSettingsModel;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.core.clock.Tick;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.observer.Modified;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ViewData {

	public static <CANVAS_VIEW    extends CommonCanvasView,
	               VIEW_DATA      extends CommonViewData,
	               CARDS_MODEL    extends CommonCanvasModel,
	               SETTINGS_MODEL extends CommonSettingsModel,
	               DASHBOARD_VIEW extends CommonDashboardView> 
	
	       void createTasks(FrontendTasks tasks, 
	    		            Class<CANVAS_VIEW>            canvasViewClass,
	    		            Class<VIEW_DATA>              viewDataClass,
	    		            Class<CARDS_MODEL>            cardsModelClass,
	    		            Class<SETTINGS_MODEL>         settingsModelClass,
	    		            Class<DASHBOARD_VIEW>         dashboardViewClass,
	    		            SubTasksLambda<FrontendTasks> subTaskLambda) {

		createViewData(tasks, viewDataClass);
		
		tasks.taskGroup("ViewData")

		     .waitsFor(created(viewDataClass))

		     .andContains(subTasks -> {

		    	 timePasses(subTasks,
		    			    viewDataClass,
		    			    canvasViewClass,
		    			    dashboardViewClass);

		    	 observeSettings(subTasks,
		    			         viewDataClass,
		    			         settingsModelClass);
		    	 
		    	 moveViewport(subTasks,
		    			      canvasViewClass);

		    	 resizeViewport(subTasks,
		    			        canvasViewClass);

		    	 mouseEvtOnViewer(subTasks,
		    			          viewDataClass);

		    	 mouseEvtOnTabletop(subTasks,
		    			            canvasViewClass);
		    	 
		    	 displayCanvasModel(subTasks,
		    			            viewDataClass,
		    			            cardsModelClass);
		    	 
		    	 subTaskLambda.createSubTasks(subTasks);

		     });
	}

	private  static <VIEW_DATA extends CommonViewData> void createViewData(FrontendTasks tasks, 
			                                                               Class<VIEW_DATA> cardsViewDataClass) {

		tasks.task(create(cardsViewDataClass))
		
		     .waitsFor("Initialization")
		
		     .executesAndReturnsCreatedValue(inputs -> {

		    	 return Ntro.factory().newInstance(cardsViewDataClass);
		     });
	}

	private static <VIEW_DATA extends CommonViewData,
	                CARDS_VIEW extends CommonCanvasView,
	                DASHBOARD_VIEW extends CommonDashboardView> 
	
	        void timePasses(FrontendTasks tasks,
	        		              Class<VIEW_DATA>      viewDataClass,
	        		              Class<CARDS_VIEW>     cardsViewClass,
	        		              Class<DASHBOARD_VIEW> dashboardViewClass) {

		tasks.task("timePasses")
		
		      .waitsFor(clock().nextTick())
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  Tick            tick          = inputs.get(clock().nextTick());
		    	  VIEW_DATA       viewData      = inputs.get(created(viewDataClass));
		    	  CARDS_VIEW      cardsView     = inputs.get(created(cardsViewClass));
		    	  DASHBOARD_VIEW  dashboardView = inputs.get(created(dashboardViewClass));
		    	  
		    	  viewData.onTimePasses(tick.elapsedTime());
		    	  viewData.displayOn(cardsView, dashboardView);

		      });
	}


	private static <VIEW_DATA extends CommonViewData,
	                SETTINGS_MODEL extends CommonSettingsModel,
	                OPTIONS extends CommonDrawingOptions> 
	
	        void observeSettings(FrontendTasks tasks,
	        		             Class<VIEW_DATA> cardsViewDataClass,
	        		             Class<SETTINGS_MODEL> settingsModelClass) {

		tasks.task("observeSettings")
		
		      .waitsFor(modified(settingsModelClass))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  VIEW_DATA                 viewData         = inputs.get(created(cardsViewDataClass));
		    	  Modified<SETTINGS_MODEL>  modifiedSettings = inputs.get(modified(settingsModelClass));
		    	  
		    	  viewData.setDrawingOptions(modifiedSettings.currentValue().drawingOptions());

		      });
	}

	private static <CARDS_VIEW extends CommonCanvasView> void moveViewport(FrontendTasks tasks,
			                                                        Class<CARDS_VIEW> cardsViewClass) {

		tasks.task("moveViewport")
		
		      .waitsFor(event(EvtMoveViewport.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  EvtMoveViewport evtMoveViewport = inputs.get(event(EvtMoveViewport.class));
		    	  CARDS_VIEW      cardsView       = inputs.get(created(cardsViewClass));
		    	  
		    	  evtMoveViewport.applyTo(cardsView);
		      });
	}

	private static <CARDS_VIEW extends CommonCanvasView> void resizeViewport(FrontendTasks tasks,
			                                                          Class<CARDS_VIEW> cardsViewClass) {
		tasks.task("resizeViewport")
		
		      .waitsFor(event(EvtResizeViewport.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  EvtResizeViewport evtResizeViewport = inputs.get(event(EvtResizeViewport.class));
		    	  CARDS_VIEW        cardsView         = inputs.get(created(cardsViewClass));
		    	  
		    	  evtResizeViewport.applyTo(cardsView);
		      });
	}

	private static <CARDS_VIEW_DATA extends CommonViewData> void mouseEvtOnViewer(FrontendTasks tasks,
			                                                                     Class<CARDS_VIEW_DATA> cardsViewDataClass) {
		tasks.task("mouseEvtOnViewer")
		
		      .waitsFor(event(MouseEvtOnMainCanvas.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  MouseEvtOnMainCanvas mouseEvtOnViewer = inputs.get(event(MouseEvtOnMainCanvas.class));
		    	  CARDS_VIEW_DATA  cardsViewData    = inputs.get(created(cardsViewDataClass));
		    	  
		    	  mouseEvtOnViewer.applyTo(cardsViewData);
		      });
	}


	private static <CARDS_VIEW extends CommonCanvasView> void mouseEvtOnTabletop(FrontendTasks tasks,
			                                                              Class<CARDS_VIEW> cardsViewClass) {
	}


	private static <VIEW_DATA    extends CommonViewData,
	                CANVAS_MODEL extends CommonCanvasModel> 
	
	        void displayCanvasModel(FrontendTasks tasks,
	        		               Class<VIEW_DATA> cardsViewDataClass,
	        		               Class<CANVAS_MODEL> canvasModelClass) {

		tasks.task("observeCanvasModel")
		
		      .waitsFor(modified(canvasModelClass))
		      
		      .thenExecutes(inputs -> {

		    	  VIEW_DATA              viewData      = inputs.get(created(cardsViewDataClass));
		    	  Modified<CANVAS_MODEL> modifiedModel = inputs.get(modified(canvasModelClass));

		    	  CANVAS_MODEL canvasModel = modifiedModel.currentValue();
		    	  
		    	  canvasModel.updateViewData(viewData);

		      });
	}
}
