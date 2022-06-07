package ca.ntro.cards.naivesort.frontend.views.fragments;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.fragments.ProcedureTestCaseFragment;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

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
	
}
