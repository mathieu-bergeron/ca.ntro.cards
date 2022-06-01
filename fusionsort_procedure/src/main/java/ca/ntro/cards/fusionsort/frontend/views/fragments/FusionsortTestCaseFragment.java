package ca.ntro.cards.fusionsort.frontend.views.fragments;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.fragments.ProcedureTestCaseFragment;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FusionsortTestCaseFragment extends ProcedureTestCaseFragment {

	@FXML
	protected Label categoryLabel;

	@FXML
	protected Button testCaseIdButton;

	@FXML
	protected Label inputSizeLabel;

	@FXML
	protected Label numberOfStepsLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("categoryLabel", categoryLabel);
		Ntro.assertNotNull("testCaseIdButton", testCaseIdButton);
		Ntro.assertNotNull("inputSizeLabel", inputSizeLabel);
		Ntro.assertNotNull("numberOfStepsLabel", numberOfStepsLabel);
		
		super.initialize(location, resources);
	}

	@Override
	protected Label categoryLabel() {
		return categoryLabel;
	}

	@Override
	protected Button testCaseIdButton() {
		return testCaseIdButton;
	}

	@Override
	protected Label inputSizeLabel() {
		return inputSizeLabel;
	}

	@Override
	protected Label numberOfStepsLabel() {
		return numberOfStepsLabel;
	}

}
