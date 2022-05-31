package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.frontend.views.fragments.ProcedureTestCaseFragment;
import javafx.scene.layout.Pane;

public abstract class ProcedureSelectionsView<TEST_CASE_FRAGMENT extends ProcedureTestCaseFragment> extends ViewFx {
	
	protected abstract Pane testCaseContainer();
	
	private Map<String, TEST_CASE_FRAGMENT> testCaseFragmentById = new HashMap<>();
	
	private long version = -1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void clearTestCases() {
		testCaseContainer().getChildren().clear();
	}

	public void insertTestCase(int index, TEST_CASE_FRAGMENT testCaseFragment) {
		testCaseFragmentById.put(testCaseFragment.testCaseId(), testCaseFragment);
		testCaseContainer().getChildren().add(index, testCaseFragment.rootNode());
	}
	
	public TEST_CASE_FRAGMENT testCaseFragment(String testCaseId) {
		return testCaseFragmentById.get(testCaseId);
	}

	public boolean hasEarlierVersion(long version) {
		return this.version < version;
	}

	public void memorizeVersion(long version) {
		this.version = version;
	}

}
