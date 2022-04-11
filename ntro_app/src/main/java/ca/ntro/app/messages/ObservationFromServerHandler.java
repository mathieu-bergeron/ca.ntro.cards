package ca.ntro.app.messages;

import ca.ntro.core.reflection.observer.Observation;

public interface ObservationFromServerHandler {

	void onObservation(String observationName, Observation<?> observation);

}
