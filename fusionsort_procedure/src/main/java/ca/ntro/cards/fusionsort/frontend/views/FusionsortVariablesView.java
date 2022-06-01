package ca.ntro.cards.fusionsort.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.ProcedureVariablesView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FusionsortVariablesView extends ProcedureVariablesView {
	
	@FXML
	private Label fusionsortVar01Label;

	@FXML
	private Label fusionsortVar02Label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("fusionsortVar01Label", fusionsortVar01Label);
		Ntro.assertNotNull("fusionsortVar02Label", fusionsortVar02Label);

		super.initialize(location, resources);
	}

}
