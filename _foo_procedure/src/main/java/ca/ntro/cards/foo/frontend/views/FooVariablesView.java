package ca.ntro.cards.foo.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.ProcedureVariablesView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FooVariablesView extends ProcedureVariablesView {
	
	@FXML
	private Label fooVar01Label;

	@FXML
	private Label fooVar02Label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("fooVar01Label", fooVar01Label);
		Ntro.assertNotNull("fooVar02Label", fooVar02Label);

		super.initialize(location, resources);
	}

}
