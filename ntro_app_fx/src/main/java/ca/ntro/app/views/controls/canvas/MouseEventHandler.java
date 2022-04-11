package ca.ntro.app.views.controls.canvas;

import javafx.scene.input.MouseEvent;

public interface MouseEventHandler {
	
	void handle(MouseEvent mouseEvent, double worldX, double worldY);

}
