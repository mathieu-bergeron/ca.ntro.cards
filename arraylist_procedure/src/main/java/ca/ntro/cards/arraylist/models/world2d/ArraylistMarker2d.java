package ca.ntro.cards.arraylist.models.world2d;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.models.world2d.ProcedureMarker2d;

public class ArraylistMarker2d extends ProcedureMarker2d<ArraylistProcedureObject2d, ArraylistProcedureWorld2d, ArraylistProcedureDrawingOptions>{

	public ArraylistMarker2d(String id) {
		super(id);
	}

	public ArraylistMarker2d(String markerId, String couleur) {
		// TODO Auto-generated constructor stub
		super(markerId);
		//NtroApp.colorFromString(couleur);
	}

}
