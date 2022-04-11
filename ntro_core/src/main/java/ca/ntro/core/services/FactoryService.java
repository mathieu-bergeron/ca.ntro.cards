package ca.ntro.core.services;

public interface FactoryService {
	
	<O> O newInstance(Class<O> _class);
	
	Object newInstance(String className);
	
	Class<?> namedClass(String className);
	
	void registerNamedClass(Class<?> _class);
	void registerNamedClass(String className, Class<?> _class);

}
