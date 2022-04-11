package services;


import ca.ntro.app.services.ExitHandler;
import ca.ntro.app.services.ExitServiceJdk;
import javafx.application.Platform;

public class ExitServiceFx extends ExitServiceJdk {

	@Override
	public void exit(ExitHandler exitHandler) {
		Platform.exit();

		super.exit(exitHandler);
	}

}
