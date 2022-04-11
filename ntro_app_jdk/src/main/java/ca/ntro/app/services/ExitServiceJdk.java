package ca.ntro.app.services;

public class ExitServiceJdk extends ExitServiceDefault {


	@Override
	public void exit(ExitHandler exitHandler) {
		super.exit(exitHandler);
		System.exit(0);
	}
}
