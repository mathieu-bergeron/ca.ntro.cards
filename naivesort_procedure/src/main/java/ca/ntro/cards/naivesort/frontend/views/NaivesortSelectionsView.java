package ca.ntro.cards.naivesort.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.naivesort.frontend.views.fragments.NaivesortTestCaseFragment;
import ca.ntro.cards.frontend.views.ProcedureSelectionsView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class NaivesortSelectionsView extends ProcedureSelectionsView<NaivesortTestCaseFragment> {
	
	@FXML
	private Pane testCaseContainer;
	
	@FXML 
	private Pane idContainer;

	@FXML 
	private Pane sizeContainer;

	@FXML 
	private Pane manualContainer;

	@FXML 
	private Pane codeContainer;

	@FXML 
	private Pane solutionContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("testCaseContainer", testCaseContainer);
		Ntro.assertNotNull("idContainer", idContainer);
		Ntro.assertNotNull("sizeContainer", sizeContainer);
		Ntro.assertNotNull("manualContainer", manualContainer);
		Ntro.assertNotNull("codeContainer", codeContainer);
		Ntro.assertNotNull("solutionContainer", solutionContainer);
		
		super.initialize(location, resources);
	}

	@Override
	protected Pane testCaseContainer() {
		return testCaseContainer;
	}

	@Override
	protected Pane idContainer() {
		return idContainer;
	}

	@Override
	protected Pane sizeContainer() {
		return sizeContainer;
	}

	@Override
	protected Pane manualContainer() {
		return manualContainer;
	}

	@Override
	protected Pane codeContainer() {
		return codeContainer;
	}

	@Override
	protected Pane solutionContainer() {
		return solutionContainer;
	}
}
