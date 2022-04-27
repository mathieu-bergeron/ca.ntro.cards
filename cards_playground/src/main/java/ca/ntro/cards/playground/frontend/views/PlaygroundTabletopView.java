package ca.ntro.cards.playground.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.frontend.views.controls.canvas.World2dCanvas;
import ca.ntro.cards.frontend.views.TabletopView;
import ca.ntro.cards.models.world2d.World2dCards;
import ca.ntro.cards.playground.frontend.views.controls.PlaygroundTabletopCanvas;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;

public class PlaygroundTabletopView extends TabletopView {
	
	@FXML
	private PlaygroundTabletopCanvas tabletopCanvas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("tabletopCanvas", tabletopCanvas);

		initializeCanvas();
	}

	private void initializeCanvas() {
		tabletopCanvas.setWorldWidth(World2dCards.INITIAL_WORLD_WIDTH);
		tabletopCanvas.setWorldHeight(World2dCards.INITIAL_WORLD_HEIGHT);
	}

	@Override
	protected World2dCanvas getCanvas() {
		return tabletopCanvas;
	}

}
