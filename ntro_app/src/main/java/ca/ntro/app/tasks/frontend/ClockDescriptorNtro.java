package ca.ntro.app.tasks.frontend;

import ca.ntro.core.clock.Tick;

public class ClockDescriptorNtro implements ClockDescriptor {

	@Override
	public FrontendSimpleTaskDescriptor<Tick> nextTick() {
		return new FrontendClockTaskDescriptorNtro<>("clock[nextTick]");
	}
	

}
