package ca.ntro.cards.playground.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import ca.ntro.cards.frontend.events.EvtResizeViewport;
import ca.ntro.cards.frontend.events.MouseEvtOnViewer;
import ca.ntro.cards.frontend.views.data.GameViewData;
import ca.ntro.cards.playground.frontend.views.PlaygroundDashboardView;
import ca.ntro.cards.playground.frontend.views.PlaygroundGameView;
import ca.ntro.cards.playground.frontend.views.data.PlaygroundGameViewData;
import ca.ntro.core.clock.Tick;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

public class GameView {

	public static void createTasks(FrontendTasks tasks) {
		
		createTabletopViewData(tasks);
		
		tasks.taskGroup("GameView")
		
		     .waitsFor("Initialization")

		     .waitsFor(created(PlaygroundGameViewData.class))

		     .andContains(subTasks -> {
		    	 
		    	 moveViewport(subTasks);
		    	 resizeViewport(subTasks);

		    	 mouseEvtOnViewer(subTasks);

		    	 displayNextImage(subTasks);

		     });
	}

	private static void mouseEvtOnViewer(FrontendTasks tasks) {
		tasks.task("mouseEvtOnViewer")
		
		      .waitsFor(event(MouseEvtOnViewer.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  MouseEvtOnViewer   mouseEvtOnViwer = inputs.get(event(MouseEvtOnViewer.class));
		    	  GameViewData       mainViewData    = inputs.get(created(PlaygroundGameViewData.class));
		    	  
		    	  mouseEvtOnViwer.applyTo(mainViewData);

		      });
	}

	private static void createTabletopViewData(FrontendTasks tasks) {
		tasks.task(create(PlaygroundGameViewData.class))
		
		     .executesAndReturnsCreatedValue(inputs -> {
		    	 
		    	 return new PlaygroundGameViewData();
		     });
	}

	private static void displayNextImage(FrontendTasks tasks) {
		tasks.task("displayNextImage")
		
		      .waitsFor(clock().nextTick())
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  Tick                    tick          = inputs.get(clock().nextTick());
		    	  GameViewData            mainViewData  = inputs.get(created(PlaygroundGameViewData.class));
		    	  PlaygroundGameView      mainView      = inputs.get(created(PlaygroundGameView.class));
		    	  PlaygroundDashboardView dashboardView = inputs.get(created(PlaygroundDashboardView.class));
		    	  
		    	  mainViewData.onTimePasses(tick.elapsedTime());
		    	  mainViewData.displayOn(mainView, dashboardView);

		      });
	}

	private static void moveViewport(FrontendTasks tasks) {
		tasks.task("moveViewport")
		
		      .waitsFor(event(EvtMoveViewport.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  EvtMoveViewport     evtMoveViewport = inputs.get(event(EvtMoveViewport.class));
		    	  PlaygroundGameView  mainView        = inputs.get(created(PlaygroundGameView.class));
		    	  
		    	  evtMoveViewport.applyTo(mainView);

		      });
	}

	private static void resizeViewport(FrontendTasks tasks) {
		tasks.task("resizeViewport")
		
		      .waitsFor(event(EvtResizeViewport.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  EvtResizeViewport   evtResizeViewport = inputs.get(event(EvtResizeViewport.class));
		    	  PlaygroundGameView  mainView          = inputs.get(created(PlaygroundGameView.class));
		    	  
		    	  evtResizeViewport.applyTo(mainView);
		    	  
		      });
	}
}
