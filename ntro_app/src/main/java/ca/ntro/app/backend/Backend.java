package ca.ntro.app.backend;

public interface Backend {

	boolean isLocalBackend();
	LocalBackend asLocalBackend();

	boolean isRemoteBackend();
	RemoteBackend asRemoteBackend();
	
	Session session();
	

}
