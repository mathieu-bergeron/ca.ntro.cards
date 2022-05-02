package ca.ntro.cards.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.cards.frontend.events.EvtHideMenu;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import ca.ntro.cards.frontend.events.EvtQuit;
import ca.ntro.cards.frontend.events.EvtResizeViewport;
import ca.ntro.cards.frontend.events.EvtShowMenu;
import ca.ntro.cards.frontend.events.MouseEvtOnViewer;
import ca.ntro.cards.frontend.events.MouseEvtOnTabletop;
import ca.ntro.cards.frontend.views.data.GameViewData;

public class CommonFrontendRegistrar {
	
	public static void registerViewData(ViewRegistrarFx registrar) {
		registrar.registerViewData(GameViewData.class);
	}

	public static void registerEvents(EventRegistrar registrar) {
		registrar.registerEvent(EvtMoveViewport.class);
		registrar.registerEvent(EvtResizeViewport.class);
		registrar.registerEvent(MouseEvtOnViewer.class);
		registrar.registerEvent(MouseEvtOnTabletop.class);

		registrar.registerEvent(EvtShowMenu.class);
		registrar.registerEvent(EvtHideMenu.class);
		registrar.registerEvent(EvtQuit.class);
	}

}
