package ca.ntro.app.services;


public class ExitServiceDefault implements ExitService {

	@Override
	public void exit(ExitHandler exitHandler) {
		exitHandler.onExit();
		System.out.println("[EXITING]\n\n\n");
	}

}
