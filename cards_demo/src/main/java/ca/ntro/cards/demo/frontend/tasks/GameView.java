package ca.ntro.cards.demo.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import ca.ntro.cards.frontend.events.EvtResizeViewport;
import ca.ntro.cards.frontend.events.MouseEvtOnTabletop;
import ca.ntro.cards.frontend.events.MouseEvtOnViewer;
import ca.ntro.cards.frontend.views.data.GameViewData;
import ca.ntro.cards.demo.frontend.views.DemoDashboardView;
import ca.ntro.cards.demo.frontend.views.DemoGameView;
import ca.ntro.cards.demo.frontend.views.data.DemoGameViewData;
import ca.ntro.cards.demo.models.DemoModel;
import ca.ntro.core.clock.Tick;
import ca.ntro.core.reflection.observer.Modified;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

public class GameView {

	public static void createTasks(FrontendTasks tasks) {
		
		createTabletopViewData(tasks);
		
		tasks.taskGroup("GameView")
		
		     .waitsFor("Initialization")

		     .waitsFor(created(DemoGameViewData.class))

		     .andContains(subTasks -> {
		    	 
		    	 moveViewport(subTasks);
		    	 resizeViewport(subTasks);

		    	 mouseEvtOnViewer(subTasks);
		    	 mouseEvtOnTabletop(subTasks);

		    	 displayNextImage(subTasks);
		    	 
		    	 displayModel(subTasks);

		     });
	}

	private static void mouseEvtOnViewer(FrontendTasks tasks) {
		tasks.task("mouseEvtOnViewer")
		
		      .waitsFor(event(MouseEvtOnViewer.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  MouseEvtOnViewer   mouseEvtOnViewer = inputs.get(event(MouseEvtOnViewer.class));
		    	  GameViewData       gameViewData     = inputs.get(created(DemoGameViewData.class));
		    	  
		    	  mouseEvtOnViewer.applyTo(gameViewData);

		      });
	}

	private static void mouseEvtOnTabletop(FrontendTasks tasks) {
		tasks.task("mouseEvtOnTabletop")
		
		      .waitsFor(event(MouseEvtOnTabletop.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  MouseEvtOnTabletop mouseEventOnTabletop = inputs.get(event(MouseEvtOnTabletop.class));
		    	  DemoGameView gameView             = inputs.get(created(DemoGameView.class));
		    	  
		    	  mouseEventOnTabletop.applyTo(gameView);

		      });
	}

	private static void createTabletopViewData(FrontendTasks tasks) {
		tasks.task(create(DemoGameViewData.class))
		
		     .executesAndReturnsCreatedValue(inputs -> {
		    	 
		    	 return new DemoGameViewData();
		     });
	}

	private static void displayNextImage(FrontendTasks tasks) {
		tasks.task("displayNextImage")
		
		      .waitsFor(clock().nextTick())
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  Tick                    tick          = inputs.get(clock().nextTick());
		    	  GameViewData            gameViewData  = inputs.get(created(DemoGameViewData.class));
		    	  DemoGameView      gameView      = inputs.get(created(DemoGameView.class));
		    	  DemoDashboardView dashboardView = inputs.get(created(DemoDashboardView.class));
		    	  
		    	  gameViewData.onTimePasses(tick.elapsedTime());
		    	  gameViewData.displayOn(gameView, dashboardView);

		      });
	}

	private static void moveViewport(FrontendTasks tasks) {
		tasks.task("moveViewport")
		
		      .waitsFor(event(EvtMoveViewport.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  EvtMoveViewport     evtMoveViewport = inputs.get(event(EvtMoveViewport.class));
		    	  DemoGameView  mainView        = inputs.get(created(DemoGameView.class));
		    	  
		    	  evtMoveViewport.applyTo(mainView);

		      });
	}

	private static void resizeViewport(FrontendTasks tasks) {
		tasks.task("resizeViewport")
		
		      .waitsFor(event(EvtResizeViewport.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  EvtResizeViewport   evtResizeViewport = inputs.get(event(EvtResizeViewport.class));
		    	  DemoGameView  mainView          = inputs.get(created(DemoGameView.class));
		    	  
		    	  evtResizeViewport.applyTo(mainView);
		    	  
		      });
	}

	private static void displayModel(FrontendTasks tasks) {
		tasks.task("displayModel")
		
		      .waitsFor(modified(DemoModel.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  GameViewData              gameViewData  = inputs.get(created(DemoGameViewData.class));
		    	  Modified<DemoModel> modifiedModel = inputs.get(modified(DemoModel.class));
		    	  
		    	  DemoModel demoModel = modifiedModel.currentValue();
		    	  
		    	  demoModel.updateViewData(gameViewData);

		      });
	}
}
