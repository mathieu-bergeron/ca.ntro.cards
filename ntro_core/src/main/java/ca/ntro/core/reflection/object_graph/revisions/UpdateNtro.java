package ca.ntro.core.reflection.object_graph.revisions;

public class      UpdateNtro 

       extends    ValueRevisionNtro
       
       implements Update {
	
	
	
	public UpdateNtro() {
		super();
	}

	public UpdateNtro(String name, Object value) {
		super(name, value);
	}


	@Override
	public boolean isUpdate() {
		return true;
	}

	@Override
	public boolean isInsert() {
		return false;
	}

	@Override
	public boolean isClear() {
		return false;
	}

	@Override
	public String revisionType() {
		return "update";
	}

}
