package ca.ntro.cards.arraylist.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.ProcedureVariablesView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ArraylistVariablesView extends ProcedureVariablesView {
	
	@FXML
	private Label arraylistVar01Label;

	@FXML
	private Label arraylistVar02Label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("arraylistVar01Label", arraylistVar01Label);
		Ntro.assertNotNull("arraylistVar02Label", arraylistVar02Label);

		super.initialize(location, resources);
	}

}
