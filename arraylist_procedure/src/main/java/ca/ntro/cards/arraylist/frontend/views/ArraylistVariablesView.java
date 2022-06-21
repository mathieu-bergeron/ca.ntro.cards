package ca.ntro.cards.arraylist.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.ProcedureVariablesView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ArraylistVariablesView extends ProcedureVariablesView {
	// Copyright (C) (2022) (Marlond Augustin) (marlondjra@gmail.com)
			//
			// This file is part of Ntro
			//
			// This is free software: you can redistribute it and/or modify
			// it under the terms of the GNU GPL3 General Public License as published by
			// the Free Software Foundation, either version 3 of the License, or
			// (at your option) any later version.
			//
			// This is distributed in the hope that it will be useful,
			// but WITHOUT ANY WARRANTY; without even the implied warranty of
			// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
			// GNU GPL3 General Public License for more details.
			//
			// You should have received a copy of the GNU GPL3 General Public License
			// along with aquiletour.  If not, see <https://www.gnu.org/licenses/>
	@FXML
	private Label arraylistVar01Label;

	@FXML
	private Label arraylistVar02Label;
	
	@FXML
	private Label arraylistVar03Label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("arraylistVar01Label", arraylistVar01Label);
		Ntro.assertNotNull("arraylistVar02Label", arraylistVar02Label);
		Ntro.assertNotNull("arraylistVar03Label", arraylistVar03Label);

		super.initialize(location, resources);
	}

	public void displayFooVar01(String fooVar01) {
		arraylistVar01Label.setText(fooVar01);
	}
	public void displayFooVar02(String fooVar02) {
		arraylistVar02Label.setText(fooVar02);
	}
	public void displayFooVar03(String fooVar03) {
		arraylistVar03Label.setText(fooVar03);
	}
}
