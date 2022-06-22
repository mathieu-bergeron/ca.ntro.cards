// Copyright (C) (2022) (Adrien Josephine-Olivier) (2066267@cmontmorency.qc.ca)

package ca.ntro.cards.fusionsort.models.world2d;

import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.cards.fusionsort.FusionsortConstants;

public class FusionsortSeparator2d extends FusionsortProcedureObject2d {

	private String id;

	public FusionsortSeparator2d(String id) {
		this.id = id;
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public void initialize() {
		setWidth(5);
		setHeight(FusionsortConstants.INITIAL_CARD_HEIGHT_MILIMETERS);
	}

	@Override
	public void draw(World2dGraphicsContext gc, FusionsortProcedureDrawingOptions options) {

	}

}
