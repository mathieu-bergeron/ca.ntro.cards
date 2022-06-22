// Copyright (C) (2022) (Adrien Josephine-Olivier) (2066267@cmontmorency.qc.ca)

package ca.ntro.cards.fusionsort.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.ProcedureVariablesView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FusionsortVariablesView extends ProcedureVariablesView {

	@FXML
	private Label sortedLabel;

	@FXML
	private Label fusionsortVar02Label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("sortedLabel", sortedLabel);
		Ntro.assertNotNull("fusionsortVar02Label", fusionsortVar02Label);

		super.initialize(location, resources);
	}

	public void displaySorted(String sorted) {
		sortedLabel.setText(sorted);
	}

	public void displayFooVar02(String fooVar02) {
		fusionsortVar02Label.setText(fooVar02);
	}

}
