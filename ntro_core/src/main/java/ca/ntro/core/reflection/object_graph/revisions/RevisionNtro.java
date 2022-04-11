package ca.ntro.core.reflection.object_graph.revisions;


public abstract class RevisionNtro 

       extends        RevisionStreamNtro

       implements Revision, Revisions {
	
	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RevisionNtro() {

	}

	public RevisionNtro(String name) {
		setName(name);
	}
	
	
	@Override
	public Update asUpdate() {
		return (Update) this;
	}

	@Override
	public Delete asDelete() {
		return (Delete) this;
	}

	@Override
	public Insert asInsert() {
		return (Insert) this;
	}

	@Override
	public Clear asClear() {
		return (Clear) this;
	}


	public abstract String revisionType();
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(revisionType());
		builder.append("(\"");
		builder.append(name());
		builder.append("\")");
		
		return builder.toString();
	}
	
	
	@Override
	public Revisions subRevisions() {
		return this;
	}

	@Override
	public String name() {
		return getName();
	}

	@Override
	public boolean targets(String name) {
		return getName().equals(name);
	}
	
	

}
