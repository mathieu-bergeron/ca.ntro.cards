package ca.ntro.app;

import ca.ntro.app.session.Session;

public interface ServerRegistrar {

	void registerPort(int i);
	void registerName(String string);
	void registerSessionClass(Class<? extends Session> sessionClass);

}
