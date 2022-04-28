package ca.ntro.cards.playground.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import ca.ntro.cards.frontend.events.EvtResizeViewport;
import ca.ntro.cards.frontend.events.MouseEvtOnViewer;
import ca.ntro.cards.frontend.views.data.MainViewData;
import ca.ntro.cards.playground.frontend.views.PlaygroundMainView;
import ca.ntro.core.clock.Tick;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

public class DisplayMainView {

	public static void createTasks(FrontendTasks tasks) {
		
		createTabletopViewData(tasks);
		
		tasks.taskGroup("DisplayMainView")
		
		     .waitsFor("InitialWindow")

		     .waitsFor(created(MainViewData.class))

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
		    	  MainViewData       mainViewData    = inputs.get(created(MainViewData.class));
		    	  
		    	  mouseEvtOnViwer.applyTo(mainViewData);

		      });
	}

	private static void createTabletopViewData(FrontendTasks tasks) {
		tasks.task(create(MainViewData.class))
		
		     .executesAndReturnsCreatedValue(inputs -> {
		    	 
		    	 return new MainViewData();
		     });
	}

	private static void displayNextImage(FrontendTasks tasks) {
		tasks.task("displayNextImage")
		
		      .waitsFor(clock().nextTick())
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  Tick                tick         = inputs.get(clock().nextTick());
		    	  MainViewData        mainViewData = inputs.get(created(MainViewData.class));
		    	  PlaygroundMainView  mainView     = inputs.get(created(PlaygroundMainView.class));
		    	  
		    	  mainViewData.onTimePasses(tick.elapsedTime());
		    	  mainViewData.displayOn(mainView);

		      });
	}

	private static void moveViewport(FrontendTasks tasks) {
		tasks.task("moveViewport")
		
		      .waitsFor(event(EvtMoveViewport.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  EvtMoveViewport     evtMoveViewport = inputs.get(event(EvtMoveViewport.class));
		    	  PlaygroundMainView  mainView        = inputs.get(created(PlaygroundMainView.class));
		    	  
		    	  evtMoveViewport.applyTo(mainView);

		      });
	}

	private static void resizeViewport(FrontendTasks tasks) {
		tasks.task("resizeViewport")
		
		      .waitsFor(event(EvtResizeViewport.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  EvtResizeViewport   evtResizeViewport = inputs.get(event(EvtResizeViewport.class));
		    	  PlaygroundMainView  mainView          = inputs.get(created(PlaygroundMainView.class));
		    	  
		    	  evtResizeViewport.applyTo(mainView);
		    	  
		      });
	}
}
