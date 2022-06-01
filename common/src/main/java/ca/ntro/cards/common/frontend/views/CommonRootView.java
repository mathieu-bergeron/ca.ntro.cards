package ca.ntro.cards.common.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;

public abstract class CommonRootView extends ViewFx {
	
	private CommonSettingsView menuView;
	private CommonCanvasView canvasView;
	private CommonMessagesView messagesView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void registerSettingsView(CommonSettingsView menuView) {
		this.menuView = menuView;
	}
	
	public void registerCanvasView(CommonCanvasView canvasView) {
		this.canvasView = canvasView;
	}
	
	public void installSubViews() {
		rootNode().getChildren().add(canvasView.rootNode());
		rootNode().getChildren().add(menuView.rootNode());
		rootNode().getChildren().add(messagesView.rootNode());

		hideMenu();
		hideMessages();
	}

	public void showMenu() {
		menuView.rootNode().setVisible(true);
	}

	public void hideMenu() {
		menuView.rootNode().setVisible(false);
	}

	public void showMessages() {
		messagesView.rootNode().setVisible(true);
	}

	public void hideMessages() {
		messagesView.rootNode().setVisible(false);
	}

	public void registerMessagesView(CommonMessagesView messagesView) {
		this.messagesView = messagesView;
	}

}
