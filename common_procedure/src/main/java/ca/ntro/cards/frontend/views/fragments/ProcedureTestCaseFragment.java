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

	protected abstract Label testCaseIdLabel();
	protected abstract Label inputSizeLabel();
	protected abstract Button manualButton();
	protected abstract Button codeButton();
	protected abstract Button solutionButton();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		MsgChangeCurrentTestCase msgChangeCurrentTestCase = NtroApp.newMessage(MsgChangeCurrentTestCase.class);
		
		manualButton().setOnAction(evtFx -> {
			
			msgChangeCurrentTestCase.setTestCaseId(testCaseId);
			msgChangeCurrentTestCase.send();
			
		});
	}

	public void displayTestCaseId(String testCaseId) {
		testCaseIdLabel().setText(testCaseId);
	}

	public void displayInputSize(String inputSize) {
		inputSizeLabel().setText(inputSize);
	}

	public void displaySolution(String numberOfSteps) {
		solutionButton().setText(numberOfSteps);
	}

	public void displayManual(String label) {
		solutionButton().setText(label);
	}

	public void displayCode(String label) {
		codeButton().setText(label);
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
