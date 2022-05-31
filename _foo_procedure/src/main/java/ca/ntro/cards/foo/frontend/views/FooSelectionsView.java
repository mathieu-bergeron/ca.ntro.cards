package ca.ntro.cards.foo.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.foo.frontend.views.fragments.FooTestCaseFragment;
import ca.ntro.cards.frontend.views.ProcedureSelectionsView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class FooSelectionsView extends ProcedureSelectionsView<FooTestCaseFragment> {
	
	@FXML
	private Pane testCaseContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("testCaseContainer", testCaseContainer);
		
		super.initialize(location, resources);
	}

	@Override
	protected Pane testCaseContainer() {
		return testCaseContainer;
	}
}
