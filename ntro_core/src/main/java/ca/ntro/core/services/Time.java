package ca.ntro.core.services;

public interface Time {
	
	void sleep(long milliseconds);
	void runAfterDelay(long milliseconds, Runnable runnable);
	void runRepeatedly(long milliseconds, Runnable runnable);
	long nowMilliseconds();
	long nowNanoseconds();

}
