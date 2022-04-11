package ca.ntro.core.reflection.object_graph.revisions;

public class InsertNtro 

       extends ValueRevisionNtro 
       
       implements Insert {

	
	public InsertNtro() {
	}

	public InsertNtro(String name, Object value) {
		super(name, value);
	}


	@Override
	public boolean isUpdate() {
		return false;
	}

	@Override
	public boolean isInsert() {
		return true;
	}

	@Override
	public boolean isClear() {
		return false;
	}

	@Override
	public String revisionType() {
		return "insert";
	}


}
