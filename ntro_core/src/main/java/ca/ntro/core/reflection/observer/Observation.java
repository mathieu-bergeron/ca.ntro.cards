package ca.ntro.core.reflection.observer;

public interface Observation<V extends Observable> {

	V previousValue();
	V currentValue();

	// TODO
	//Revisions revisions();

}
