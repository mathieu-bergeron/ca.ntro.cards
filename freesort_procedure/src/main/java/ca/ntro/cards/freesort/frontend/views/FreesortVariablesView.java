package ca.ntro.cards.freesort.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.ProcedureVariablesView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FreesortVariablesView extends ProcedureVariablesView {
	// Copyright (C) (2022) (Marlond Augustin) (202043906@cmontmorency.qc.ca)
	//
	// This file is part of Ntro
	//
	// This is free software: you can redistribute it and/or modify
	// it under the terms of the GNU Affero General Public License as published by
	// the Free Software Foundation, either version 3 of the License, or
	// (at your option) any later version.
	//
	// This is distributed in the hope that it will be useful,
	// but WITHOUT ANY WARRANTY; without even the implied warranty of
	// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	// GNU Affero General Public License for more details.
	//
	// You should have received a copy of the GNU Affero General Public License
	// along with aquiletour.  If not, see <https://www.gnu.org/licenses/>
	@FXML
	private Label freesortVar01Label;

	@FXML
	private Label freesortVar02Label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("freesortVar01Label", freesortVar01Label);
		Ntro.assertNotNull("freesortVar02Label", freesortVar02Label);

		super.initialize(location, resources);
	}

	public void displayFooVar01(String fooVar01) {
		freesortVar01Label.setText(fooVar01);
	}
	//méthode ajouter, Elle sera utile pour dire si les cartes sont triées ou non
	public void displayFooVar02(String fooVar02) {
		freesortVar02Label.setText(fooVar02);
	}

}
