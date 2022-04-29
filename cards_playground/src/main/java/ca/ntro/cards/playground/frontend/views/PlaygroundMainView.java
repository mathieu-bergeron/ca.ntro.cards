package ca.ntro.cards.playground.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.app.views.controls.canvas.World2dResizableCanvasFx;
import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.cards.frontend.views.MainView;
import ca.ntro.cards.playground.frontend.views.controls.PlaygroundTabletopCanvas;
import ca.ntro.cards.playground.frontend.views.controls.PlaygroundViewerCanvas;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class PlaygroundMainView extends MainView {
	
	@FXML
	private PlaygroundTabletopCanvas tabletopCanvas;

	@FXML
	private PlaygroundViewerCanvas viewerCanvas;

	@FXML
	private StackPane dashboardContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("tabletopCanvas", tabletopCanvas);
		Ntro.assertNotNull("viewerCanvas", viewerCanvas);
		Ntro.assertNotNull("dashboardContainer", dashboardContainer);

		super.initialize(location, resources);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected World2dResizableCanvasFx getViewerCanvas() {
		return viewerCanvas;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected World2dCanvasFx getTabletopCanvas() {
		return tabletopCanvas;
	}

	@Override
	protected Pane getDashboardContainer() {
		return dashboardContainer;
	}


}
