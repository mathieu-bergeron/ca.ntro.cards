package ca.ntro.app.messages;

import ca.ntro.core.reflection.observer.Observation;

public class ObservationFromServerHandlerNull implements ObservationFromServerHandler {

	@Override
	public void onObservation(String observationName, Observation<?> observation) {
	}

}
