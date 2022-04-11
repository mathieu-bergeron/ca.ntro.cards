package ca.ntro.app.backend;

public class BackendNtro implements Backend {

	@Override
	public boolean isLocalBackend() {
		return false;
	}

	@Override
	public LocalBackend asLocalBackend() {
		return (LocalBackend) this;
	}

	@Override
	public RemoteBackend asRemoteBackend() {
		return (RemoteBackend) this;
	}

	@Override
	public Session session() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRemoteBackend() {
		// TODO Auto-generated method stub
		return false;
	}


}
