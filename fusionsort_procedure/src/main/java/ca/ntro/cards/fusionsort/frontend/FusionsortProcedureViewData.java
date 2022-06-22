// Copyright (C) (2022) (Adrien Josephine-Olivier) (2066267@cmontmorency.qc.ca)

package ca.ntro.cards.fusionsort.frontend;

import ca.ntro.app.views.controls.canvas.World2dMouseEventFx;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortCard2d;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortProcedureDrawingOptions;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortProcedureObject2d;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortProcedureWorld2d;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortSeparator2d;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.models.world2d.ProcedureCard2d;
import javafx.scene.input.MouseEvent;

public class FusionsortProcedureViewData extends
		ProcedureViewData<FusionsortProcedureObject2d, FusionsortProcedureWorld2d, FusionsortProcedureDrawingOptions> {

	private String controlKeyState = "null";

	public String getControlKeyState() {
		return controlKeyState;
	}

	public void setControlKeyState(String controlKeyState) {
		this.controlKeyState = controlKeyState;
	}

	@Override
	protected FusionsortProcedureWorld2d newWorld2d() {
		return new FusionsortProcedureWorld2d();
	}

	@Override
	protected ProcedureCard2d newCard2d(AbstractCard card) {
		return new FusionsortCard2d(card);
	}

	@Override
	protected FusionsortProcedureDrawingOptions defaultDrawingOptions() {
		return new FusionsortProcedureDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}

	public void addOrUpdateSeparator(String separatorId, double topLeftX, double topLeftY) {

		FusionsortSeparator2d separator2d = null;

		separator2d = (FusionsortSeparator2d) world2d().objectById(separatorId);

		if (separator2d == null) {
			separator2d = new FusionsortSeparator2d(separatorId);
			world2d().addObject2d((FusionsortProcedureObject2d) separator2d);
		}

		separator2d.setTopLeftX(topLeftX);
		separator2d.setTopLeftY(topLeftY);

	}

	@Override
	public void dispatchMouseEvent(World2dMouseEventFx world2dMouseEventFx) {
		super.dispatchMouseEvent(world2dMouseEventFx);

		
		if (controlKeyState.equals("pressed")) {
			MouseEvent evtFx = world2dMouseEventFx.rawMouseEvent();
			if (evtFx.getEventType().equals(MouseEvent.MOUSE_PRESSED) && evtFx.isPrimaryButtonDown()) {
				String id = "tmp";
				addOrUpdateSeparator(id, world2dMouseEventFx.worldX(), world2dMouseEventFx.worldY());
				world2d().buildAndSendManualModel();

			}

		}

	}

}
