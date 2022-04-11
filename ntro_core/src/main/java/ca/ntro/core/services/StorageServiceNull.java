package ca.ntro.core.services;

import ca.ntro.core.path.Path;
import ca.ntro.core.storage.FileWatcher;

public class StorageServiceNull implements StorageService {

	@Override
	public String readTextFile(Path filePath) {
		return null;
	}

	@Override
	public void writeTextFile(Path filePath, String content) {
	}

	@Override
	public boolean ifFileExists(Path filePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void watchFile(Path filePath, FileWatcher watcher) {
		// TODO Auto-generated method stub
		
	}

}
