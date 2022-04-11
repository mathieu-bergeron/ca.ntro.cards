package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;

public abstract class RootView extends ViewFx {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void displaySubView(ViewFx subView) {
		rootNode().getChildren().clear();
		rootNode().getChildren().add(subView.rootNode());
	}

}
