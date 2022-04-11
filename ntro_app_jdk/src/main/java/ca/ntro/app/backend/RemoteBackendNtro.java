package ca.ntro.app.backend;


import ca.ntro.app.ServerRegistrarNtro;

public abstract class RemoteBackendNtro 

	   extends        BackendNtro

       implements     RemoteBackend {
	
	private ServerRegistrarNtro serverRegistrar = new ServerRegistrarNtro();
	
	public RemoteBackendNtro() {
		registerServer(serverRegistrar);
	}
	
	@Override
	public void openConnection() {
		WebSocketClientNtro client = new WebSocketClientNtro(serverRegistrar.getServerName(), serverRegistrar.getPort());
		client.connect();
	}

	@Override
	public boolean isRemoteBackend() {
		return true;
	}

	@Override
	public boolean isLocalBackend() {
		return false;
	}

}
