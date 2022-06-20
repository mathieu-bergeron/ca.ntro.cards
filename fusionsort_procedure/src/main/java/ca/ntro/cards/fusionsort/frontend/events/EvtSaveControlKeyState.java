// Copyright (C) (2022) (Adrien Josephine-Olivier) (2066267@cmontmorency.qc.ca)

package ca.ntro.cards.fusionsort.frontend.events;

import ca.ntro.app.frontend.events.EventNtro;

public class EvtSaveControlKeyState extends EventNtro {

	private String controlKeyState;

	public EvtSaveControlKeyState() {
		
	}

	public String getControlKeyState() {
		return controlKeyState;
	}

	public void setControlKeyState(String controlKeyState) {
		this.controlKeyState = controlKeyState;
	}

	
	
}
