package ca.ntro.cards.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import ca.ntro.cards.frontend.events.EvtResizeViewport;
import ca.ntro.cards.frontend.events.MouseEvtOnTabletop;
import ca.ntro.cards.frontend.events.MouseEvtOnViewer;
import ca.ntro.cards.frontend.events.EvtStartCodeExecution;
import ca.ntro.cards.frontend.views.CardsView;
import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.SettingsModel;
import ca.ntro.core.clock.Tick;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.observer.Modified;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class Cards {

	public static <CARDS_VIEW extends CardsView,
	               CARDS_VIEW_DATA extends CardsViewData,
	               CARDS_MODEL extends CardsModel,
	               SETTINGS_MODEL extends SettingsModel,
	               DASHBOARD_VIEW extends DashboardView> 
	
	
	       void createTasks(FrontendTasks tasks, 
	    		            Class<CARDS_VIEW> cardsViewClass,
	    		            Class<CARDS_VIEW_DATA> cardsViewDataClass,
	    		            Class<CARDS_MODEL> cardsModelClass,
	    		            Class<SETTINGS_MODEL> settingsModelClass,
	    		            Class<DASHBOARD_VIEW> dashboardViewClass,
	    		            SubTasksLambda<FrontendTasks> subTaskLambda) {
		
		createCardsViewData(tasks, cardsViewDataClass);
		
		tasks.taskGroup("Cards")

		     .waitsFor("Initialization")

		     .waitsFor(created(cardsViewDataClass))

		     .andContains(subTasks -> {

		    	 observeSettings(subTasks,
		    			         cardsViewDataClass,
		    			         settingsModelClass);
		    	 
		    	 moveViewport(subTasks,
		    			      cardsViewClass);

		    	 resizeViewport(subTasks,
		    			        cardsViewClass);

		    	 mouseEvtOnViewer(subTasks,
		    			          cardsViewDataClass);

		    	 mouseEvtOnTabletop(subTasks,
		    			            cardsViewClass);

		    	 displayNextImage(subTasks,
		    			          cardsViewDataClass,
		    			          cardsViewClass,
		    			          dashboardViewClass);
		    	 
		    	 displayCardsModel(subTasks,
		    			      cardsViewDataClass,
		    			      cardsModelClass);
		    	 
		    	 startCodeExecution(subTasks, cardsViewDataClass);
		    	 
		    	 subTaskLambda.createSubTasks(subTasks);

		     });
	}

	private  static <CARDS_VIEW_DATA extends CardsViewData> void createCardsViewData(FrontendTasks tasks, 
			                                                                         Class<CARDS_VIEW_DATA> cardsViewDataClass) {

		tasks.task(create(cardsViewDataClass))
		
		     .executesAndReturnsCreatedValue(inputs -> {

		    	 return Ntro.factory().newInstance(cardsViewDataClass);
		     });
	}

	private static <CARDS_VIEW_DATA extends CardsViewData,
	                SETTINGS_MODEL extends SettingsModel> 
	
	        void observeSettings(FrontendTasks tasks,
	        		             Class<CARDS_VIEW_DATA> cardsViewDataClass,
	        		             Class<SETTINGS_MODEL> settingsModelClass) {

		tasks.task("observeSettings")
		
		      .waitsFor(modified(settingsModelClass))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  CardsViewData            gameViewData     = inputs.get(created(cardsViewDataClass));
		    	  Modified<SETTINGS_MODEL> modifiedSettings = inputs.get(modified(settingsModelClass));
		    	  
		    	  gameViewData.setDrawingOptions(modifiedSettings.currentValue());

		      });
	}

	private static <CARDS_VIEW extends CardsView> void moveViewport(FrontendTasks tasks,
			                                                        Class<CARDS_VIEW> cardsViewClass) {

		tasks.task("moveViewport")
		
		      .waitsFor(event(EvtMoveViewport.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  EvtMoveViewport evtMoveViewport = inputs.get(event(EvtMoveViewport.class));
		    	  CARDS_VIEW      cardsView       = inputs.get(created(cardsViewClass));
		    	  
		    	  evtMoveViewport.applyTo(cardsView);
		      });
	}

	private static <CARDS_VIEW extends CardsView> void resizeViewport(FrontendTasks tasks,
			                                                          Class<CARDS_VIEW> cardsViewClass) {
		tasks.task("resizeViewport")
		
		      .waitsFor(event(EvtResizeViewport.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  EvtResizeViewport evtResizeViewport = inputs.get(event(EvtResizeViewport.class));
		    	  CARDS_VIEW        cardsView         = inputs.get(created(cardsViewClass));
		    	  
		    	  evtResizeViewport.applyTo(cardsView);
		      });
	}

	private static <CARDS_VIEW_DATA extends CardsViewData> void mouseEvtOnViewer(FrontendTasks tasks,
			                                                                     Class<CARDS_VIEW_DATA> cardsViewDataClass) {
		tasks.task("mouseEvtOnViewer")
		
		      .waitsFor(event(MouseEvtOnViewer.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  MouseEvtOnViewer mouseEvtOnViewer = inputs.get(event(MouseEvtOnViewer.class));
		    	  CARDS_VIEW_DATA  cardsViewData    = inputs.get(created(cardsViewDataClass));
		    	  
		    	  mouseEvtOnViewer.applyTo(cardsViewData);
		      });
	}


	private static <CARDS_VIEW extends CardsView> void mouseEvtOnTabletop(FrontendTasks tasks,
			                                                              Class<CARDS_VIEW> cardsViewClass) {
		tasks.task("mouseEvtOnTabletop")
		
		      .waitsFor(event(MouseEvtOnTabletop.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  MouseEvtOnTabletop mouseEventOnTabletop = inputs.get(event(MouseEvtOnTabletop.class));
		    	  CARDS_VIEW         cardsView            = inputs.get(created(cardsViewClass));
		    	  
		    	  mouseEventOnTabletop.applyTo(cardsView);
		      });
	}


	private static <CARDS_VIEW_DATA extends CardsViewData,
	                CARDS_VIEW extends CardsView,
	                DASHBOARD_VIEW extends DashboardView> 
	
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

	private static <CARDS_VIEW_DATA extends CardsViewData>
	
	        void startCodeExecution(FrontendTasks tasks,
	        		                Class<CARDS_VIEW_DATA> cardsViewDataClass) {

		tasks.task("executeCode")

		      .waitsFor(event(EvtStartCodeExecution.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  CARDS_VIEW_DATA cardsViewData = inputs.get(created(cardsViewDataClass));
		    	  cardsViewData.startCodeExecution();

		      });
	}



	private static <CARDS_VIEW_DATA extends CardsViewData,
	                CARDS_MODEL extends CardsModel> 
	
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
