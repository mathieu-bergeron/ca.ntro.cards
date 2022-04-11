package ca.ntro.app;

import ca.ntro.app.session.Session;

public class ServerRegistrarNtro implements ServerRegistrar {
	
	private int port = 8001;
	private String serverName = "localhost";
	private Class<? extends Session> sessionClass;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Class<? extends Session> getSessionClass() {
		return sessionClass;
	}

	public void setSessionClass(Class<? extends Session> sessionClass) {
		this.sessionClass = sessionClass;
	}

	@Override
	public void registerPort(int port) {
		setPort(port);
	}

	@Override
	public void registerName(String serverName) {
		setServerName(serverName);
	}

	@Override
	public void registerSessionClass(Class<? extends Session> sessionClass) {
		setSessionClass(sessionClass);
	}

}
