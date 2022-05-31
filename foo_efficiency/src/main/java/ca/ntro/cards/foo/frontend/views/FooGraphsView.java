package ca.ntro.cards.foo.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.controls.canvas.World2dResizableCanvasFx;
import ca.ntro.cards.foo.FooConstants;
import ca.ntro.cards.foo.frontend.views.controls.FooEfficiencyMainCanvas;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyGraphsView;
import ca.ntro.cards.efficiency.frontend.views.controls.EfficiencyMainCanvas;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FooGraphsView extends EfficiencyGraphsView {

	@FXML
	private VBox cardsViewContainer;

	@FXML
	private FooEfficiencyMainCanvas viewerCanvas;

	@FXML
	private StackPane dashboardContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("cardsViewContainer", cardsViewContainer);
		Ntro.assertNotNull("viewerCanvas", viewerCanvas);
		Ntro.assertNotNull("dashboardContainer", dashboardContainer);

		super.initialize(location, resources);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected World2dResizableCanvasFx mainCanvas() {
		return viewerCanvas;
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
		return FooConstants.INITIAL_WORLD_HEIGHT;
	}

	@Override
	protected double initialWorldWidth() {
		return FooConstants.INITIAL_WORLD_WIDTH;
	}

	@Override
	public void displayViewport() {

		
	}


}
