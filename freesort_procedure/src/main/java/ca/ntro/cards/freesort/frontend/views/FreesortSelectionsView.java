package ca.ntro.cards.freesort.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.freesort.frontend.views.fragments.FreesortTestCaseFragment;
import ca.ntro.cards.frontend.views.ProcedureSelectionsView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class FreesortSelectionsView extends ProcedureSelectionsView<FreesortTestCaseFragment> {
	
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

	@Override
	protected Pane idContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Pane sizeContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Pane manualContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Pane codeContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Pane solutionContainer() {
		// TODO Auto-generated method stub
		return null;
	}
}
