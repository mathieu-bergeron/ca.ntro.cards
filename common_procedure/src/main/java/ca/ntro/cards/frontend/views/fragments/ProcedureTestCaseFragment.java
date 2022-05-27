package ca.ntro.cards.frontend.views.fragments;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class ProcedureTestCaseFragment extends ViewFx {

	protected abstract Label categoryLabel();
	protected abstract Button testCaseIdButton();
	protected abstract Label inputSizeLabel();
	protected abstract Label numberOfStepsLabel();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	public void displayCategory(String category) {
		categoryLabel().setText(category);
	}

	public void displayTestCaseId(String testCaseId) {
		testCaseIdButton().setText(testCaseId);
	}

	public void displayInputSize(String inputSize) {
		inputSizeLabel().setText(inputSize);
	}

	public void displayNumberOfSteps(String numberOfSteps) {
		numberOfStepsLabel().setText(numberOfSteps);
	}


}
