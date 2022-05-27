package ca.ntro.cards.demo.frontend.views.fragments;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.fragments.ProcedureTestCaseFragment;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DemoTestCaseFragment extends ProcedureTestCaseFragment {

	@FXML
	protected Label categoryLabel;

	@FXML
	protected Label testCaseIdLabel;

	@FXML
	protected Label inputSizeLabel;

	@FXML
	protected Label numberOfStepsLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("categoryLabel", categoryLabel);
		Ntro.assertNotNull("testCaseIdLabel", testCaseIdLabel);
		Ntro.assertNotNull("inputSizeLabel", inputSizeLabel);
		Ntro.assertNotNull("numberOfStepsLabel", numberOfStepsLabel);
		
		super.initialize(location, resources);
	}

	@Override
	protected Label categoryLabel() {
		return categoryLabel;
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
	protected Label numberOfStepsLabel() {
		return numberOfStepsLabel;
	}

}
