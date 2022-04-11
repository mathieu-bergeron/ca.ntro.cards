package ca.ntro.core.clock;

public class TickNtro implements Tick {
	
	private double elapsed;
	

	public double getElapsed() {
		return elapsed;
	}

	public void setElapsed(double elapsed) {
		this.elapsed = elapsed;
	}
	
	public TickNtro() {
		
	}

	public TickNtro(double elapsed) {
		setElapsed(elapsed);
	}

	@Override
	public double elapsedTime() {
		return getElapsed();
	}

}
