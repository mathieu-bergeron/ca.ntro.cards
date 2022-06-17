package ca.ntro.cards.intro.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.ProcedureVariablesView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class IntroVariablesView extends ProcedureVariablesView {
	
	@FXML
	private Label introVar01Label;

	@FXML
	private Label introVar02Label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("introVar01Label", introVar01Label);
		Ntro.assertNotNull("introVar02Label", introVar02Label);

		super.initialize(location, resources);
	}

}
