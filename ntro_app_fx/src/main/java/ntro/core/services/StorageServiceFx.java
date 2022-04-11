package ntro.core.services;

import ca.ntro.core.services.StorageServiceJdk;
import ca.ntro.core.storage.FileWatcher;
import javafx.application.Platform;

public class StorageServiceFx extends StorageServiceJdk {
	
	// FIXME: this should be done in a ThreadService

	@Override
	protected void callWatcher(FileWatcher watcher) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				watcher.fileChanged();
			}
		});
	}
	

}
