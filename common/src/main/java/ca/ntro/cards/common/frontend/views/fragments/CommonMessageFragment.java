package ca.ntro.cards.common.frontend.views.fragments;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;
import javafx.scene.control.Label;

public abstract class CommonMessageFragment extends ViewFx {
	
	protected abstract Label messageLabel();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
