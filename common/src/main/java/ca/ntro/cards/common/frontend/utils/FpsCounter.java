package ca.ntro.cards.common.frontend.utils;

import ca.ntro.core.initialization.Ntro;

public class FpsCounter {

	private static long RECOMPUTE_AT_EVERY_X_MILISECONDS = 200;

	private long lastTimestamp = Ntro.time().nowMilliseconds();
	private long framesDisplaySinceLastTimestamp = 0;

	private double currentFps = 0;
	
	public double currentFps() {
		return currentFps;
	}

	public void onNewFrame() {
		framesDisplaySinceLastTimestamp++;

		computeFpsIfNeeded();
	}

	private void computeFpsIfNeeded() {
		long now = Ntro.time().nowMilliseconds();
		long elapsedMilliseconds = now - lastTimestamp;

		if(elapsedMilliseconds > RECOMPUTE_AT_EVERY_X_MILISECONDS) {
			computeFps(elapsedMilliseconds);

			framesDisplaySinceLastTimestamp = 0;
			lastTimestamp = now;
		}
	}

	private void computeFps(long elapsedMilliseconds) {
		double elapsedSeconds = elapsedMilliseconds / 1E3;
		currentFps = framesDisplaySinceLastTimestamp / elapsedSeconds;
	}
	

}
