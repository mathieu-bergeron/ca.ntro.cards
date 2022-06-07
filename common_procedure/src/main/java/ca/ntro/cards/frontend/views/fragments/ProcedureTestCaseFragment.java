package ca.ntro.cards.frontend.views.fragments;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.common.test_cases.descriptor.AbstractAttemptDescriptor;
import ca.ntro.cards.messages.MsgChangeCurrentTestCase;
import ca.ntro.cards.test_cases.descriptor.ProcedureAttemptDescriptor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class ProcedureTestCaseFragment extends ViewFx {
	
	private String testCaseId;

	protected abstract Label testCaseIdLabel();
	protected abstract Label inputSizeLabel();
	protected abstract Button manualButton();
	protected abstract Button codeButton();
	protected abstract Button solutionButton();
	
	private String solutionText;
	private String errorText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		MsgChangeCurrentTestCase msgChangeCurrentTestCase = NtroApp.newMessage(MsgChangeCurrentTestCase.class);
		
		manualButton().setOnAction(evtFx -> {
			
			msgChangeCurrentTestCase.setTestCaseId(testCaseId);
			msgChangeCurrentTestCase.send();
			
		});
		
		this.solutionText = resources.getString("solutionText");
		this.errorText = resources.getString("errorText");
	}

	public void displayTestCaseId(String testCaseId) {
		testCaseIdLabel().setText(testCaseId);
	}

	public void displayInputSize(String inputSize) {
		inputSizeLabel().setText(inputSize);
	}

	public void displaySolution(AbstractAttemptDescriptor attempt) {
		solutionButton().setText(String.valueOf(attempt.numberOfSteps()));
		displayIsCurrentAttempt(solutionButton(), attempt);
	}

	public void displayManual(AbstractAttemptDescriptor attempt) {

		displayIsASolution(manualButton(), attempt);

	}

	public void displayCode(AbstractAttemptDescriptor attempt) {

		displayIsASolution(codeButton(), attempt);
		displayIsCurrentAttempt(codeButton(), attempt);

	}

	private void displayIsASolution(Button button, AbstractAttemptDescriptor attempt) {
		if(attempt.isASolution()) {

			button.setText(solutionText);

		}else {

			button.setText(errorText);
		}

	}

	private void displayIsCurrentAttempt(Button button, AbstractAttemptDescriptor attempt) {
		if(attempt.isCurrentAttempt()) {

			button.setStyle("-fx-background-color: blue");

		}else {

			button.setStyle("-fx-background-color: gray");

		}

	}

	public void enableTestCaseSelection() {
		manualButton().setDisable(false);
		codeButton().setDisable(false);
		solutionButton().setDisable(false);
	}

	public void disableTestCaseSelection() {
		manualButton().setDisable(true);
		codeButton().setDisable(true);
		solutionButton().setDisable(true);
	}

	public void memorizeTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	
	public String testCaseId() {
		return testCaseId;
	}


}
