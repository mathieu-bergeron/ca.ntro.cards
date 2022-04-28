package ca.ntro.cards.playground.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import ca.ntro.cards.frontend.events.EvtResizeViewport;
import ca.ntro.cards.frontend.events.MouseEvtOnMainDisplay;
import ca.ntro.cards.frontend.views.data.TabletopViewData;
import ca.ntro.cards.playground.frontend.views.PlaygroundMainView;
import ca.ntro.core.clock.Tick;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

public class DisplayTabletop {

	public static void createTasks(FrontendTasks tasks) {
		
		createTabletopViewData(tasks);
		
		tasks.taskGroup("DisplayTabletop")
		
		     .waitsFor("InitialWindow")

		     .waitsFor(created(TabletopViewData.class))

		     .andContains(subTasks -> {
		    	 
		    	 moveViewport(subTasks);
		    	 resizeViewport(subTasks);
		    	 mouseEvtOnMainDisplay(subTasks);

		    	 displayNextImage(subTasks);

		     });
	}

	private static void mouseEvtOnMainDisplay(FrontendTasks tasks) {
		tasks.task("mouseEvtOnMainDisplay")
		
		      .waitsFor(event(MouseEvtOnMainDisplay.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  MouseEvtOnMainDisplay   mouseEvtOnMainDisplay = inputs.get(event(MouseEvtOnMainDisplay.class));
		    	  TabletopViewData        tabletopData          = inputs.get(created(TabletopViewData.class));
		    	  
		    	  mouseEvtOnMainDisplay.applyTo(tabletopData);

		      });
	}

	private static void createTabletopViewData(FrontendTasks tasks) {
		tasks.task(create(TabletopViewData.class))
		
		     .executesAndReturnsCreatedValue(inputs -> {
		    	 
		    	 return new TabletopViewData();
		     });
	}

	private static void displayNextImage(FrontendTasks tasks) {
		tasks.task("displayNextImage")
		
		      .waitsFor(clock().nextTick())
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  Tick                    tick         = inputs.get(clock().nextTick());
		    	  TabletopViewData        tabletopData = inputs.get(created(TabletopViewData.class));
		    	  PlaygroundMainView  tabletopView = inputs.get(created(PlaygroundMainView.class));
		    	  
		    	  tabletopData.onTimePasses(tick.elapsedTime());
		    	  tabletopData.displayOn(tabletopView);

		      });
	}

	private static void moveViewport(FrontendTasks tasks) {
		tasks.task("moveViewport")
		
		      .waitsFor(event(EvtMoveViewport.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  EvtMoveViewport         evtMoveViewport = inputs.get(event(EvtMoveViewport.class));
		    	  PlaygroundMainView  tabletopView    = inputs.get(created(PlaygroundMainView.class));
		    	  
		    	  evtMoveViewport.applyTo(tabletopView);

		      });
	}

	private static void resizeViewport(FrontendTasks tasks) {
		tasks.task("resizeViewport")
		
		      .waitsFor(event(EvtResizeViewport.class))
		      
		      .thenExecutes(inputs -> {
		    	  
		    	  EvtResizeViewport       evtResizeViewport = inputs.get(event(EvtResizeViewport.class));
		    	  PlaygroundMainView  tabletopView      = inputs.get(created(PlaygroundMainView.class));
		    	  
		    	  evtResizeViewport.applyTo(tabletopView);
		    	  
		      });
	}
}
