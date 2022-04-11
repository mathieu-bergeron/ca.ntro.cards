package ca.ntro.core.services;


import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.json.JsonObject;
import ca.ntro.core.reflection.JsonObjectGraphJdk;
import ca.ntro.core.reflection.ObjectGraphJdk;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.stream.Stream;

public class ReflectionServiceJdk extends ReflectionServiceNtro { 

	@Override
	public ObjectGraph graphFromObject(Object o) {
		return new ObjectGraphJdk(o);
	}

	@Override
	public ObjectGraph graphFromObject(Object o, String graphName) {
		return new ObjectGraphJdk(o, graphName);
	}

	@Override
	public ObjectGraph graphFromJsonObject(JsonObject jsonObject) {
		return new JsonObjectGraphJdk(jsonObject);
	}

	@Override
	public ObjectGraph graphFromJsonObject(JsonObject jsonObject, String graphName) {
		return new JsonObjectGraphJdk(jsonObject, graphName);
	}
	

	@Override
	public char asChar(Object object) {
		if(object == null) return 0;
		
		double real = asDouble(object);
		long rounded = Math.round(real);
		
		return (char) rounded;
	}

	@Override
	public int asInt(Object object) {
		if(object == null) return 0;

		double real = asDouble(object);
		long rounded = Math.round(real);
		
		return (int) rounded;
	}

	@Override
	public long asLong(Object object) {
		if(object == null) return 0;

		double real = asDouble(object);
		long rounded = Math.round(real);
		
		return rounded;
	}

	@Override
	public float asFloat(Object object) {
		if(object == null) return 0;

		return Float.parseFloat(object.toString());
	}

	@Override
	public double asDouble(Object object) {
		if(object == null) return 0;

		return Double.parseDouble(object.toString());
	}

	@Override
	public boolean isList(Object object) {
		return object instanceof List 
				|| (object != null && object.getClass().isArray());
	}

	@Override
	public List<Object> asList(Object object) {
		List<Object> list;
		
		if(object != null 
				&& object.getClass().isArray()) {

			list = new ArrayList<>();
			
			for(int i = 0; i < Array.getLength(object); i++) {
				list.add(Array.get(object, i));
			}
			
		}else {
			
			list = (List<Object>) object;
		}
		
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <I> List<I> asList(Object object, Class<I> itemClass) {
		List<I> list;
		
		if(object != null
				&& object.getClass().isArray()) {

			list = new ArrayList<>();
			
			for(int i = 0; i < Array.getLength(object); i++) {
				list.add((I) Array.get(object, i));
			}
			
		}else {
			
			list = (List<I>) object;
		}
		
		return list;
	}

	@Override
	public String simpleName(Class<?> _class) {
		return _class.getSimpleName();
	}

	@Override
	public Method getMethodByName(Class<?> _class, String methodName) {
		Method method = null;
		
		for(Method candidate : _class.getMethods()) {
			if(candidate.getName().equals(methodName)) {
				method = candidate;
				break;
			}
		}
		
		return method;
	}

	@Override
	public boolean ifClassImplements(Class<?> _class, Class<?> _interface) {
		boolean ifImplements = false;
		
		for(Class<?> _candidate : _class.getInterfaces()) {
			if(_candidate.equals(_interface)) {
				ifImplements = true;
				break;
			}
		}
		
		if(!ifImplements) {
			Class<?> superClass = _class.getSuperclass();
			if(superClass != null) {
				ifImplements = ifClassImplements(superClass, _interface);
			}
		}

		return ifImplements;
	}





}
