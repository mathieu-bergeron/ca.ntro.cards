package ca.ntro.app.backend;

public abstract class LocalBackendNtro 

       extends        BackendNtro

       implements     LocalBackend {

	@Override
	public boolean isLocalBackend() {
		return true;
	}

	@Override
	public Session session() {
		return null;
	}
}
