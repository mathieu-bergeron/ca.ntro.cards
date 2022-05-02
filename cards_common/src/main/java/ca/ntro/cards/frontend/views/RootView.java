package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;

public abstract class RootView extends ViewFx {
	
	private MenuView menuView;
	private GameView gameView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void registerMenuView(MenuView menuView) {
		this.menuView = menuView;
	}
	
	public void registerGameView(GameView gameView) {
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
