package ca.ntro.core.services;

public interface ExceptionService {
	
	void throwException(Throwable t);

	void enterCatchingMode();
	void exitCatchingMode();

	boolean hasException();
	Throwable exception();

}
