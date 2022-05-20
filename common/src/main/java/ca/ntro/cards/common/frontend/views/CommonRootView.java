package ca.ntro.cards.common.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;

public abstract class CommonRootView extends ViewFx {
	
	private CommonSettingsView menuView;
	private CommonCanvasView gameView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void registerSettingsView(CommonSettingsView menuView) {
		this.menuView = menuView;
	}
	
	public void registerCardsView(CommonCanvasView gameView) {
		this.gameView = gameView;
	}
	
	public void installSubViews() {
		rootNode().getChildren().add(gameView.rootNode());
		rootNode().getChildren().add(menuView.rootNode());

		hideMenu();
	}

	public void showMenu() {
		menuView.rootNode().setVisible(true);
	}

	public void hideMenu() {
		menuView.rootNode().setVisible(false);
	}

}
