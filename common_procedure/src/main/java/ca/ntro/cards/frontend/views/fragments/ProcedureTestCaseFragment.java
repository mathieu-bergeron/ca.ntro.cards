package ca.ntro.cards.frontend.views.fragments;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;
import javafx.scene.control.Label;

public abstract class ProcedureTestCaseFragment extends ViewFx {

	protected abstract Label categoryLabel();
	protected abstract Label testCaseIdLabel();
	protected abstract Label inputSizeLabel();
	protected abstract Label numberOfStepsLabel();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}


}
