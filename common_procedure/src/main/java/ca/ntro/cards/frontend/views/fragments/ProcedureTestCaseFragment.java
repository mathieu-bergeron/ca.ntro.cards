package ca.ntro.cards.frontend.views.fragments;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.messages.MsgChangeCurrentTestCase;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class ProcedureTestCaseFragment extends ViewFx {
	
	private String testCaseId;

	protected abstract Label categoryLabel();
	protected abstract Button testCaseIdButton();
	protected abstract Label inputSizeLabel();
	protected abstract Label numberOfStepsLabel();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		MsgChangeCurrentTestCase msgChangeCurrentTestCase = NtroApp.newMessage(MsgChangeCurrentTestCase.class);
		
		testCaseIdButton().setOnAction(evtFx -> {
			
			msgChangeCurrentTestCase.setTestCaseId(testCaseIdButton().getText());
			msgChangeCurrentTestCase.send();
			
		});
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
	public void enableTestCaseSelection() {
		testCaseIdButton().setDisable(false);
	}
	public void disableTestCaseSelection() {
		testCaseIdButton().setDisable(true);
	}

	public void memorizeTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	
	public String testCaseId() {
		return testCaseId;
	}


}
