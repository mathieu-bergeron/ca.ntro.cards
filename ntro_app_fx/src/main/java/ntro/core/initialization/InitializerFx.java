package ntro.core.initialization;

import ca.ntro.core.initialization.InitializerJdk;
import ca.ntro.core.services.StorageService;
import ca.ntro.core.services.Time;
import ntro.core.services.StorageServiceFx;
import ntro.core.services.TimeFx;

public class InitializerFx extends InitializerJdk {

	@Override
	protected Time provideTime() {
		return new TimeFx();
	}

	@Override
	protected StorageService provideStorageService() {
		return new StorageServiceFx();
	}

}
