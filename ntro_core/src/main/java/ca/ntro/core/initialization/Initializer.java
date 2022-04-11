package ca.ntro.core.initialization;


public interface Initializer {
	
	Initializer setOptions(InitializerOptions options);
	Object execute();
	void executeBlocking();


}
