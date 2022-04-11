package ca.ntro.core.services;

public class TimeNull implements Time {

	@Override
	public void sleep(long miliseconds) {
	}

	@Override
	public long nowMilliseconds() {
		return 0;
	}

	@Override
	public void runAfterDelay(long milliseconds, Runnable runnable) {
	}

	@Override
	public void runRepeatedly(long milliseconds, Runnable runnable) {
		
	}

	@Override
	public long nowNanoseconds() {
		// TODO Auto-generated method stub
		return 0;
	}

}
