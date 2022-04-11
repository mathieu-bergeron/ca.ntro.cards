package ca.ntro.core.path;

public interface ValuePath extends GenericPath<ValuePath> {

	public static ValuePath fromRawPath(String rawPath) {
		ValuePathNtro valuePath = new ValuePathNtro();
		
		valuePath.fromRawPath(rawPath);
		
		return valuePath;
	}

}
