package ca.ntro.core.services;

import ca.ntro.core.path.Path;
import ca.ntro.core.storage.FileWatcher;

public interface StorageService {
	
	boolean ifFileExists(Path filePath);

	String readTextFile(Path filePath);
	void writeTextFile(Path filePath, String content);
	
	void watchFile(Path filePath, FileWatcher watcher);
	


}
