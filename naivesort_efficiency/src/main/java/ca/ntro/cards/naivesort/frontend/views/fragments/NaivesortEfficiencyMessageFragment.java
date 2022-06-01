package ca.ntro.cards.naivesort.frontend.views.fragments;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.efficiency.frontend.views.fragments.EfficiencyMessageFragment;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NaivesortEfficiencyMessageFragment extends EfficiencyMessageFragment {
	
	@FXML
	private Label messageLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Ntro.assertNotNull("messageLabel", messageLabel);
		
		super.initialize(location, resources);
	}

	@Override
	protected Label messageLabel() {
		return messageLabel;
	}

}
