package ca.ntro.cards.arraylist.frontend.views.controls;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.frontend.views.controls.ProcedureMainCanvas;

public class ArraylistMainCanvas extends ProcedureMainCanvas {

	public void displayRecycleBin() {
		// TODO Auto-generated method stub
		drawOnViewport(gc -> {
			gc.setFill(NtroApp.colorFromString("3399ff"));
			gc.fillRect(0,0, 100, 100);
			gc.strokeText("Recycle", 25, 50);
		});
	}

}
