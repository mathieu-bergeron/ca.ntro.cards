// Copyright (C) (2022) (Zein El Abdin Hazimeh) (2037668@cmontmorency.qc.ca)
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

package ca.ntro.cards.freesort.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.frontend.views.ProcedureMessagesView;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class FreesortProcedureMessagesView extends ProcedureMessagesView {
		
	 

	@FXML
	private Pane topSpace;

	@FXML
	private Button quitMessageButton;


	@FXML
	private Pane messagesContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		 
		Ntro.assertNotNull("topSpace", topSpace);
		 
		Ntro.assertNotNull("messagesContainer", messagesContainer);
		
		 
		Ntro.assertNotNull("quitMessageButton", quitMessageButton);
		
		super.initialize(location, resources);
	}

	@Override
	protected Stream<Pane> spaces() {
		return new StreamNtro<Pane>() {
			@Override
			public void forEach_(Visitor<Pane> visitor) throws Throwable {
				 
				visitor.visit(topSpace);
				 
			}
		};
	}

	@Override
	protected Pane messagesContainer() {
		return messagesContainer;
	}

	@Override
	protected Button quitMessageButton() {
		 
		return quitMessageButton;
	}

}
