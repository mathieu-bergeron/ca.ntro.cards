package ca.ntro.cards.playground.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.TabletopView;
import ca.ntro.cards.playground.frontend.views.controls.PlaygroundTabletopCanvas;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;

public class PlaygroundTabletopView extends TabletopView {
	
	@FXML
	private PlaygroundTabletopCanvas tabletopCanvas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		
		Ntro.assertNotNull("tabletopCanvas", tabletopCanvas);
		
		initializeCanvas();
	}

	private void initializeCanvas() {
	}

}
