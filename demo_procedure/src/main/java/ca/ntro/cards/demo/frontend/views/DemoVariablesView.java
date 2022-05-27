package ca.ntro.cards.demo.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.ProcedureVariablesView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DemoVariablesView extends ProcedureVariablesView {
	
	@FXML
	private Label indexOfSmallestLabel;

	@FXML
	private Label indexOfCandidateLabel;

	@FXML
	private Label indexOfNextEmptyLabel;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("indexOfSmallestLabel", indexOfSmallestLabel);
		Ntro.assertNotNull("indexOfCandidateLabel", indexOfCandidateLabel);
		Ntro.assertNotNull("indexOfNextEmptyLabel", indexOfNextEmptyLabel);
		
		super.initialize(location, resources);
	}

	public void displayIndexOfSmallest(String indexOfSmallest) {
		indexOfSmallestLabel.setText(indexOfSmallest);
	}


	public void displayIndexOfCandidate(String indexOfCandidate) {
		indexOfCandidateLabel.setText(indexOfCandidate);
	}

	public void displayIndexOfNextEmpty(String indexOfNextEmpty) {
		indexOfNextEmptyLabel.setText(indexOfNextEmpty);
	}

}
