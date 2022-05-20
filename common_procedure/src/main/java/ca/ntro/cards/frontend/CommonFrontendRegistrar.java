package ca.ntro.cards.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.common.frontend.events.EvtHideMenu;
import ca.ntro.cards.common.frontend.events.EvtMoveViewport;
import ca.ntro.cards.common.frontend.events.EvtQuit;
import ca.ntro.cards.common.frontend.events.EvtResizeViewport;
import ca.ntro.cards.common.frontend.events.EvtShowMenu;
import ca.ntro.cards.common.frontend.events.MouseEvtOnTabletop;
import ca.ntro.cards.common.frontend.events.MouseEvtOnViewer;

public class CommonFrontendRegistrar {
	
	public static void registerViewData(ViewRegistrarFx registrar) {
		registrar.registerViewData(CommonViewData.class);
	}

	public static void registerEvents(EventRegistrar registrar) {
	}

}
