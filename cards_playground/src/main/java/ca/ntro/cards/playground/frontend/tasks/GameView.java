package ca.ntro.cards.playground.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import ca.ntro.cards.frontend.events.EvtResizeViewport;
import ca.ntro.cards.frontend.events.MouseEvtOnTabletop;
import ca.ntro.cards.frontend.events.MouseEvtOnViewer;
import ca.ntro.cards.frontend.views.data.GameViewData;
import ca.ntro.cards.playground.frontend.views.PlaygroundDashboardView;
import ca.ntro.cards.playground.frontend.views.PlaygroundGameView;
import ca.ntro.cards.playground.frontend.views.data.PlaygroundGameViewData;
import ca.ntro.cards.playground.models.PlaygroundSettingsModel;
import ca.ntro.core.clock.Tick;
import ca.ntro.core.reflection.observer.Modified;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

public class GameView {

	public static void createTasks(FrontendTasks tasks) {
		
		createTabletopViewData(tasks);
		
		tasks.taskGroup("GameView")
		
		     .waitsFor("Initialization")

		     .waitsFor(created(PlaygroundGameViewData.class))

		     .andContains(subTasks -> {
		    	 
		    	 
		    	 observeSettings(subTasks);
		    	 
		    	 moveViewport(subTasks);
		    	 resizeViewport(subTasks);

		    	 mouseEvtOnViewer(subTasks);
		    	 mouseEvtOnTabletop(subTasks);

		    	 displayNextImage(subTasks);

		     });
	}

	private static void observeSettings(FrontendTasks tasks) {
		tasks.task("observeSettings")
		
		      .waitsFor(modified(PlaygroundSettingsModel.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  GameViewData                      gameViewData     = inputs.get(created(PlaygroundGameViewData.class));
		    	  Modified<PlaygroundSettingsModel> modifiedSettings = inputs.get(modified(PlaygroundSettingsModel.class));
		    	  
		    	  gameViewData.setDrawingOptions(modifiedSettings.currentValue());

		      });
	}

	private static void mouseEvtOnViewer(FrontendTasks tasks) {
		tasks.task("mouseEvtOnViewer")
		
		      .waitsFor(event(MouseEvtOnViewer.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  MouseEvtOnViewer   mouseEvtOnViewer = inputs.get(event(MouseEvtOnViewer.class));
		    	  GameViewData       gameViewData     = inputs.get(created(PlaygroundGameViewData.class));
		    	  
		    	  mouseEvtOnViewer.applyTo(gameViewData);

		      });
	}

	private static void mouseEvtOnTabletop(FrontendTasks tasks) {
		tasks.task("mouseEvtOnTabletop")
		
		      .waitsFor(event(MouseEvtOnTabletop.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  MouseEvtOnTabletop mouseEventOnTabletop = inputs.get(event(MouseEvtOnTabletop.class));
		    	  PlaygroundGameView gameView             = inputs.get(created(PlaygroundGameView.class));
		    	  
		    	  mouseEventOnTabletop.applyTo(gameView);

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
		    	  GameViewData            gameViewData  = inputs.get(created(PlaygroundGameViewData.class));
		    	  PlaygroundGameView      gameView      = inputs.get(created(PlaygroundGameView.class));
		    	  PlaygroundDashboardView dashboardView = inputs.get(created(PlaygroundDashboardView.class));
		    	  
		    	  gameViewData.onTimePasses(tick.elapsedTime());
		    	  gameViewData.displayOn(gameView, dashboardView);

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
