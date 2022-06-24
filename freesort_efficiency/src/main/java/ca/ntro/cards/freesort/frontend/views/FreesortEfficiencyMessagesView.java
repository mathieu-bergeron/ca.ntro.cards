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

import ca.ntro.cards.efficiency.frontend.views.EfficiencyMessagesView;
import ca.ntro.core.stream.Stream;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class FreesortEfficiencyMessagesView extends EfficiencyMessagesView {
	 

	@Override
	protected Stream<Pane> spaces() {
		return null;
	}

	@Override
	protected Pane messagesContainer() {
		return null;
	}

	@Override
	protected Button quitMessageButton() {
	 
		return null;
	}

}
