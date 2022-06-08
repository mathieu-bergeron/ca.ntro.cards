package ca.ntro.cards.naivesort.frontend.views.fragments;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.fragments.ProcedureTestCaseFragment;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class NaivesortTestCaseFragment extends ProcedureTestCaseFragment {

	@FXML
	protected Pane idContainer;

	@FXML
	protected Pane sizeContainer;

	@FXML
	protected Pane manualContainer;

	@FXML
	protected Pane codeContainer;

	@FXML
	protected Pane solutionContainer;
			
	@FXML
	protected Label testCaseIdLabel;

	@FXML
	protected Label inputSizeLabel;

	@FXML
	protected Button manualButton;

	@FXML
	protected Button codeButton;

	@FXML
	protected Button solutionButton;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("idContainer", idContainer);
		Ntro.assertNotNull("sizeContainer", sizeContainer);
		Ntro.assertNotNull("manualContainer", manualContainer);
		Ntro.assertNotNull("codeContainer", codeContainer);
		Ntro.assertNotNull("solutionContainer", solutionContainer);

		Ntro.assertNotNull("testCaseIdLabel", testCaseIdLabel);
		Ntro.assertNotNull("inputSizeLabel", inputSizeLabel);
		Ntro.assertNotNull("manualButton", manualButton);
		Ntro.assertNotNull("codeButton", codeButton);
		Ntro.assertNotNull("solutionButton", solutionButton);
		
		
		super.initialize(location, resources);
	}

	@Override
	protected Label testCaseIdLabel() {
		return testCaseIdLabel;
	}

	@Override
	protected Label inputSizeLabel() {
		return inputSizeLabel;
	}

	@Override
	protected Button manualButton() {
		return manualButton;
	}

	@Override
	protected Button codeButton() {
		return codeButton;
	}

	@Override
	protected Button solutionButton() {
		return solutionButton;
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
