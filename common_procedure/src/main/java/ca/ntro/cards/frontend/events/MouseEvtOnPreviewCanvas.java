package ca.ntro.cards.frontend.events;

import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.app.frontend.views.controls.canvas.World2dMouseEvent;
import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.cards.common.frontend.views.CommonCanvasView;
import javafx.scene.input.MouseEvent;

public class MouseEvtOnPreviewCanvas extends EventNtro {
	
	private World2dMouseEventFx world2dMouseEventFx;

	public World2dMouseEventFx getWorld2dMouseEventFx() {
		return world2dMouseEventFx;
	}

	public void setWorld2dMouseEventFx(World2dMouseEventFx world2dMouseEventFx) {
		this.world2dMouseEventFx = world2dMouseEventFx;
	}


	public void applyTo(CommonCanvasView gameView) {
		gameView.mouseEvtOnTabletop(world2dMouseEventFx);
	}

}
