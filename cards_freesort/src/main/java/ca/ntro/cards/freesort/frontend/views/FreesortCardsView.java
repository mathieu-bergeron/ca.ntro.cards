package ca.ntro.cards.freesort.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.app.views.controls.canvas.World2dResizableCanvasFx;
import ca.ntro.cards.frontend.views.CardsView;
import ca.ntro.cards.freesort.FreesortConstants;
import ca.ntro.cards.freesort.frontend.views.controls.FreesortTabletopCanvas;
import ca.ntro.cards.freesort.frontend.views.controls.FreesortViewerCanvas;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FreesortCardsView extends CardsView {

	@FXML
	private VBox cardsViewContainer;

	@FXML
	private FreesortTabletopCanvas tabletopCanvas;

	@FXML
	private FreesortViewerCanvas viewerCanvas;

	@FXML
	private StackPane dashboardContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("cardsViewContainer", cardsViewContainer);
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
		return cardsViewContainer;
	}

	@Override
	protected double initialWorldHeight() {
		return FreesortConstants.INITIAL_WORLD_HEIGHT;
	}

	@Override
	protected double initialWorldWidth() {
		return FreesortConstants.INITIAL_WORLD_WIDTH;
	}

	@Override
	protected double initialTabletopScreenHeight() {
		return FreesortConstants.INITIAL_TABLETOP_SCREEN_HEIGHT;
	}


}
