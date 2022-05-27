package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.frontend.views.fragments.ProcedureTestCaseFragment;
import javafx.scene.layout.Pane;

public abstract class ProcedureSelectionsView<TEST_CASE_FRAGMENT extends ProcedureTestCaseFragment> extends ViewFx {
	
	protected abstract Pane testCaseContainer();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void clearTestCases() {
		testCaseContainer().getChildren().clear();
	}

	public void addTestCase(TEST_CASE_FRAGMENT testCaseFragment) {
		testCaseContainer().getChildren().add(testCaseFragment.rootNode());
	}

}
