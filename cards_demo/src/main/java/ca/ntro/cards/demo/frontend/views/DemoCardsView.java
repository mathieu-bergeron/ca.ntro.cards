package ca.ntro.cards.demo.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.app.views.controls.canvas.World2dResizableCanvasFx;
import ca.ntro.cards.frontend.views.CardsView;
import ca.ntro.cards.demo.DemoConstants;
import ca.ntro.cards.demo.frontend.views.controls.DemoTabletopCanvas;
import ca.ntro.cards.demo.frontend.views.controls.DemoViewerCanvas;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DemoCardsView extends CardsView {

	@FXML
	private VBox gameViewContainer;

	@FXML
	private DemoTabletopCanvas tabletopCanvas;

	@FXML
	private DemoViewerCanvas viewerCanvas;

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
	protected Pane mainContainer() {
		return gameViewContainer;
	}

	@Override
	protected double initialWorldHeight() {
		return DemoConstants.INITIAL_WORLD_HEIGHT;
	}

	@Override
	protected double initialWorldWidth() {
		return DemoConstants.INITIAL_WORLD_WIDTH;
	}

	@Override
	protected double initialTabletopScreenHeight() {
		return DemoConstants.INITIAL_TABLETOP_SCREEN_HEIGHT;
	}


}
