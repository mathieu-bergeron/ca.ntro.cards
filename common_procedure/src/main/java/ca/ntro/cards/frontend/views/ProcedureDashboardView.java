package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import javafx.scene.layout.Pane;

public abstract class ProcedureDashboardView extends CommonDashboardView {
	
	protected abstract Pane selectionsContainer();

	protected abstract Pane replayControlsContainer();

	protected abstract Pane variablesContainer();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		
	}

	public void installSelectionsView(SelectionsView selectionsView) {
		if(selectionsContainer() != null) {
			selectionsContainer().getChildren().clear();
			selectionsContainer().getChildren().add(selectionsView.rootNode());
		}
	}

	public void installReplayControlsView(ReplayControlsView replayControlsView) {
		if(replayControlsContainer() != null) {
			replayControlsContainer().getChildren().clear();
			replayControlsContainer().getChildren().add(replayControlsView.rootNode());
		}
	}

	public void installVariablesView(VariablesView variablesView) {
		if(variablesContainer() != null) {
			variablesContainer().getChildren().clear();
			variablesContainer().getChildren().add(variablesView.rootNode());
		}
	}


}
