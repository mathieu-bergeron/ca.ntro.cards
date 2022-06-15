package ca.ntro.cards.arraylist.frontend.views.controls;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.frontend.views.controls.ProcedureMainCanvas;

public class ArraylistMainCanvas extends ProcedureMainCanvas {

	public void displayRecycleBin() {
		// TODO Auto-generated method stub
		drawOnViewport(gc -> {
			gc.setFill(NtroApp.colorFromString("3399ff"));
			gc.fillText("Recycle", 200, 200);
			gc.fillRect(0,0, 100, 100);
		});
	}

}
