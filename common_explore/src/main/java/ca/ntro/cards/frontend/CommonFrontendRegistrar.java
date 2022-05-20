package ca.ntro.cards.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import common.frontend.events.EvtHideMenu;
import common.frontend.events.EvtMoveViewport;
import common.frontend.events.EvtQuit;
import common.frontend.events.EvtResizeViewport;
import common.frontend.events.EvtShowMenu;
import common.frontend.events.MouseEvtOnTabletop;
import common.frontend.events.MouseEvtOnViewer;
import common.frontend.views.data.CardsViewData;

public class CommonFrontendRegistrar {
	
	public static void registerViewData(ViewRegistrarFx registrar) {
		registrar.registerViewData(CardsViewData.class);
	}

	public static void registerEvents(EventRegistrar registrar) {
	}

}
