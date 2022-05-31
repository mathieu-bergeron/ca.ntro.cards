package ca.ntro.cards.freesort.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.ProcedureVariablesView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FreesortVariablesView extends ProcedureVariablesView {
	
	@FXML
	private Label freesortVar01Label;

	@FXML
	private Label freesortVar02Label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("freesortVar01Label", freesortVar01Label);
		Ntro.assertNotNull("freesortVar02Label", freesortVar02Label);

		super.initialize(location, resources);
	}

	public void displayFooVar01(String fooVar01) {
		freesortVar01Label.setText(fooVar01);
	}

}
