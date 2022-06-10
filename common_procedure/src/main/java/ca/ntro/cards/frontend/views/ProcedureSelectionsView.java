package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.frontend.views.fragments.ProcedureTestCaseFragment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class ProcedureSelectionsView<TEST_CASE_FRAGMENT extends ProcedureTestCaseFragment> extends ViewFx {

	protected abstract Pane idContainer();

	protected abstract Pane sizeContainer();

	protected abstract Pane manualContainer();

	protected abstract Pane codeContainer();

	protected abstract Pane solutionContainer();
	
	protected abstract Pane testCaseContainer();
	
	private Map<String, TEST_CASE_FRAGMENT> testCaseFragmentById = new HashMap<>();
	
	private long version = -1;
	
	private double idLeftX = -1;
	private double sizeLeftX = -1;
	private double manualLeftX = -1;
	private double codeLeftX = -1;
	private double solutionLeftX = -1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(idContainer() != null) {
			idContainer().layoutXProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					idLeftX = newValue.doubleValue();
				}
			});
		}

		if(sizeContainer() != null) {
			sizeContainer().layoutXProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					System.out.println("sizeLeftX: " + newValue.doubleValue());
					/*
					for(Node child : testCaseContainer().getChildren()) {
						child.setTranslateX(newValue.doubleValue());
						//child.setLayoutX(newValue.doubleValue());
					}
					*/
				}
			});
		}

	}

	public void clearTestCases() {
		testCaseContainer().getChildren().clear();
	}

	public void insertTestCase(int index, TEST_CASE_FRAGMENT testCaseFragment) {
		testCaseFragmentById.put(testCaseFragment.testCaseId(), testCaseFragment);
		testCaseContainer().getChildren().add(index, testCaseFragment.rootNode());

		if(idContainer() != null) {
			testCaseFragment.idNode().translateXProperty().bind(idContainer().layoutXProperty());
		}
		
		if(sizeContainer() != null) {
			testCaseFragment.sizeNode().translateXProperty().bind(sizeContainer().layoutXProperty());
		}

		if(manualContainer() != null) {
			testCaseFragment.manualNode().translateXProperty().bind(manualContainer().layoutXProperty());
		}

		if(codeContainer() != null) {
			testCaseFragment.codeNode().translateXProperty().bind(codeContainer().layoutXProperty());
		}

		if(solutionContainer() != null) {
			testCaseFragment.solutionNode().translateXProperty().bind(solutionContainer().layoutXProperty());
		}
		
		
		
		
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
