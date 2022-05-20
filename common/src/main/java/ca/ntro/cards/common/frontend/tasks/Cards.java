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

public class Cards {

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
		
		createCardsViewData(tasks, viewDataClass);
		
		tasks.taskGroup("Cards")

		     .waitsFor("Initialization")

		     .waitsFor(created(viewDataClass))

		     .andContains(subTasks -> {

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

		    	 displayNextImage(subTasks,
		    			          viewDataClass,
		    			          canvasViewClass,
		    			          dashboardViewClass);
		    	 
		    	 displayCardsModel(subTasks,
		    			           viewDataClass,
		    			           cardsModelClass);
		    	 
		    	 subTaskLambda.createSubTasks(subTasks);

		     });
	}

	private  static <CARDS_VIEW_DATA extends CommonViewData> void createCardsViewData(FrontendTasks tasks, 
			                                                                         Class<CARDS_VIEW_DATA> cardsViewDataClass) {

		tasks.task(create(cardsViewDataClass))
		
		     .executesAndReturnsCreatedValue(inputs -> {

		    	 return Ntro.factory().newInstance(cardsViewDataClass);
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


	private static <CARDS_VIEW_DATA extends CommonViewData,
	                CARDS_VIEW extends CommonCanvasView,
	                DASHBOARD_VIEW extends CommonDashboardView> 
	
	        void displayNextImage(FrontendTasks tasks,
	        		              Class<CARDS_VIEW_DATA> cardsViewDataClass,
	        		              Class<CARDS_VIEW> cardsViewClass,
	        		              Class<DASHBOARD_VIEW> dashboardViewClass) {

		tasks.task("displayNextImage")
		
		      .waitsFor(clock().nextTick())
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  Tick            tick          = inputs.get(clock().nextTick());
		    	  CARDS_VIEW_DATA cardsViewData = inputs.get(created(cardsViewDataClass));
		    	  CARDS_VIEW      cardsView     = inputs.get(created(cardsViewClass));
		    	  DASHBOARD_VIEW  dashboardView = inputs.get(created(dashboardViewClass));
		    	  
		    	  cardsViewData.onTimePasses(tick.elapsedTime());
		    	  cardsViewData.displayOn(cardsView, dashboardView);

		      });
	}

	private static <CARDS_VIEW_DATA extends CommonViewData,
	                CARDS_MODEL     extends CommonCanvasModel> 
	
	        void displayCardsModel(FrontendTasks tasks,
	        		               Class<CARDS_VIEW_DATA> cardsViewDataClass,
	        		               Class<CARDS_MODEL> cardsModelClass) {

		tasks.task("displayCardsModel")
		
		      .waitsFor(modified(cardsModelClass))
		      
		      .thenExecutes(inputs -> {

		    	  CARDS_VIEW_DATA       gameViewData  = inputs.get(created(cardsViewDataClass));
		    	  Modified<CARDS_MODEL> modifiedModel = inputs.get(modified(cardsModelClass));

		    	  CARDS_MODEL demoModel = modifiedModel.currentValue();
		    	  
		    	  demoModel.updateViewData(gameViewData);
		      });
	}
}
