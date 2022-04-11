package ca.ntro.app.backend;

import ca.ntro.app.ServerRegistrar;

public interface RemoteBackend extends Backend {

	public void registerServer(ServerRegistrar registrar);
	
	void openConnection();
	
}
