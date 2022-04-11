package ca.ntro.core.reflection.object_graph.revisions;

import ca.ntro.core.path.ValuePath;

public abstract class ValueRevisionNtro 

       extends        RevisionNtro 

       implements     ValueRevision {
	
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public ValueRevisionNtro() {
	}

	public ValueRevisionNtro(String name, Object value) {
		super(name);
		setValue(value);
	}
	

	@Override
	public boolean isDelete() {
		return false;
	}

	@Override
	public Object value() {
		return getValue();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(revisionType());
		builder.append("(\"");
		builder.append(name());
		builder.append("\", ");
		builder.append(value());
		builder.append(")");

		return builder.toString();
	}

}
