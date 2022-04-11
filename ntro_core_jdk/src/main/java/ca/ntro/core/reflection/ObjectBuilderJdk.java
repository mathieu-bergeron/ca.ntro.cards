package ca.ntro.core.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_graph.ObjectBuilderNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;

public class ObjectBuilderJdk extends ObjectBuilderNtro {

	public ObjectBuilderJdk(ObjectGraphNtro graph) {
		super(graph);
	}

	@Override
	protected void setAttribute(Object object, String attributeName, Object attributeValue) {
		String setterName = ReflectionUtils.setterNameFromAttributeName(attributeName);
		
		invokeSetter(object, setterName, attributeValue);
	}

	public static void invokeSetter(Object object, String setterName, Object attributeValue) {

		try {

			Method method = Ntro.reflection().getMethodByName(object.getClass(), setterName);
			
			if(method == null) {
				Ntro.throwException("[FATAL] setter not found: " + setterName(object, setterName));
			}

			method.invoke(object, attributeValue);

		} catch (IllegalArgumentException e) {
			
			Ntro.throwException("[FATAL] cannot invoke setter " + setterName(object, setterName) + " with parameter of type " + attributeValue.getClass());

		} catch (SecurityException | IllegalAccessException | InvocationTargetException e) {

			Ntro.throwException(e);
		}
	}
	
	private static String setterName(Object object, String setterName) {
		return Ntro.reflection().simpleName(object.getClass()) + "." + setterName;
	}


}
