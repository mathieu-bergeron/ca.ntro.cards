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
package ca.ntro.cards.common.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.common.frontend.events.EvtHideMenu;
import ca.ntro.cards.common.frontend.events.EvtQuit;
import ca.ntro.cards.common.frontend.events.EvtShowMenu;
import ca.ntro.cards.common.messages.MsgToggleUseFourCardColors;
import ca.ntro.core.stream.Stream;
import javafx.beans.value.WritableDoubleValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class CommonSettingsView extends ViewFx {
	
	protected abstract Stream<Pane> spaces();
	
	protected abstract Button quitButton();
	
	protected abstract Button quitSettingButton();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		initializeSpaces();
		initializeQuitButton();
		initializeQuitSettingButton();

	}

	private void initializeQuitSettingButton() {
		 
		EvtHideMenu evtHideMenu = NtroApp.newEvent(EvtHideMenu.class);
		
			quitSettingButton().setOnAction(evtFx -> {
				evtHideMenu.trigger();
			});
	 
	}

	private void initializeQuitButton() {
		EvtQuit evtQuit = NtroApp.newEvent(EvtQuit.class);
		
		if(quitButton() != null) {
			quitButton().setOnAction(evtFx -> {
				evtQuit.trigger();
			});
		}
	}

	private void initializeSpaces() {
		EvtHideMenu evtHideMenu = NtroApp.newEvent(EvtHideMenu.class);
		
		if(spaces() != null) {
			spaces().forEach(space -> {
				space.addEventFilter(MouseEvent.MOUSE_CLICKED, evtFx -> {
					evtHideMenu.trigger();
				});
			});
		}
	}

 
 
}
