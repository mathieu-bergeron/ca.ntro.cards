package ca.ntro.cards.common.frontend.events;

import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.cards.common.frontend.CommonViewData;
import javafx.scene.input.MouseEvent;

public class MouseEvtOnMainCanvas extends EventNtro {
	
	private World2dMouseEventFx world2dMouseEventFx;
	
	public World2dMouseEventFx getWorld2dMouseEventFx() {
		return world2dMouseEventFx;
	}

	public void setWorld2dMouseEventFx(World2dMouseEventFx world2dMouseEventFx) {
		this.world2dMouseEventFx = world2dMouseEventFx;
	}

	public void applyTo(CommonViewData tabletopData) {
		tabletopData.dispatchMouseEvent(world2dMouseEventFx);
	}



}
