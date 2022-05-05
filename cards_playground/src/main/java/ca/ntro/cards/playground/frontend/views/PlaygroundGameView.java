package ca.ntro.cards.playground.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.app.views.controls.canvas.World2dResizableCanvasFx;
import ca.ntro.cards.frontend.views.CardsView;
import ca.ntro.cards.playground.PlaygroundConstants;
import ca.ntro.cards.playground.frontend.views.controls.PlaygroundTabletopCanvas;
import ca.ntro.cards.playground.frontend.views.controls.PlaygroundViewerCanvas;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PlaygroundGameView extends CardsView {

	@FXML
	private VBox gameViewContainer;

	@FXML
	private PlaygroundTabletopCanvas tabletopCanvas;

	@FXML
	private PlaygroundViewerCanvas viewerCanvas;

	@FXML
	private StackPane dashboardContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("gameViewContainer", gameViewContainer);
		Ntro.assertNotNull("tabletopCanvas", tabletopCanvas);
		Ntro.assertNotNull("viewerCanvas", viewerCanvas);
		Ntro.assertNotNull("dashboardContainer", dashboardContainer);

		super.initialize(location, resources);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected World2dResizableCanvasFx viewerCanvas() {
		return viewerCanvas;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected World2dCanvasFx tabletopCanvas() {
		return tabletopCanvas;
	}

	@Override
	protected Pane dashboardContainer() {
		return dashboardContainer;
	}

	@Override
	protected Pane cardsViewContainer() {
		return gameViewContainer;
	}

	@Override
	protected double initialWorldHeight() {
		return PlaygroundConstants.INITIAL_WORLD_HEIGHT;
	}

	@Override
	protected double initialWorldWidth() {
		return PlaygroundConstants.INITIAL_WORLD_WIDTH;
	}

	@Override
	protected double initialTabletopScreenHeight() {
		return PlaygroundConstants.INITIAL_TABLETOP_SCREEN_HEIGHT;
	}


}
