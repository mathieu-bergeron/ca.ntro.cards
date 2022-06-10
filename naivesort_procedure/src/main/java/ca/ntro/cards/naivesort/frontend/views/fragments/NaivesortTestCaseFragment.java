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
	protected Label testCaseIdLabel;

	@FXML
	protected Label inputSizeLabel;

	@FXML
	protected Button manualButton;

	@FXML
	protected Button codeButton;

	@FXML
	protected Button solutionButton;

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
		Ntro.assertNotNull("testCaseIdLabel", testCaseIdLabel);
		Ntro.assertNotNull("inputSizeLabel", inputSizeLabel);
		Ntro.assertNotNull("manualButton", manualButton);
		Ntro.assertNotNull("codeButton", codeButton);
		Ntro.assertNotNull("solutionButton", solutionButton);

		Ntro.assertNotNull("idContainer", idContainer);
		Ntro.assertNotNull("sizeContainer", sizeContainer);
		Ntro.assertNotNull("manualContainer", manualContainer);
		Ntro.assertNotNull("codeContainer", codeContainer);
		Ntro.assertNotNull("solutionContainer", solutionContainer);
		
		
		
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
	public Pane idContainer() {
		return idContainer;
	}

	@Override
	public Pane sizeContainer() {
		return sizeContainer;
	}

	@Override
	public Pane manualContainer() {
		return manualContainer;
	}

	@Override
	public Pane codeContainer() {
		return codeContainer;
	}

	@Override
	public Pane solutionContainer() {
		return solutionContainer;
	}
	
}
