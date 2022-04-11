package ca.ntro.app.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class DefaultRootView extends StackPane {
	
	public DefaultRootView() {
		super();
		
		setAlignment(Pos.CENTER);
		getChildren().add(new Label("[NtroAppFx default view]"));
	}

}
