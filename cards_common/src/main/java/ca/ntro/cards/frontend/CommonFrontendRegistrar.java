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
import ca.ntro.cards.frontend.views.data.CardsViewData;

public class CommonFrontendRegistrar {
	
	public static void registerViewData(ViewRegistrarFx registrar) {
		registrar.registerViewData(CardsViewData.class);
	}

	public static void registerEvents(EventRegistrar registrar) {
	}

}
