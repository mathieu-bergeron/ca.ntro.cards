package ca.ntro.core.reflection.observer;

public class ObservationNtro<O extends Observable> 
       implements Observation<O>,
                  Modified<O> {
	
	private O previousValue;
	private O currentValue;

	public O getPreviousValue() {
		return previousValue;
	}

	public void setPreviousValue(O previousValue) {
		this.previousValue = previousValue;
	}

	public O getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(O currentValue) {
		this.currentValue = currentValue;
	}
	
	
	

	@Override
	public O previousValue() {
		return getPreviousValue();
	}

	@Override
	public O currentValue() {
		return getCurrentValue();
	}

}
