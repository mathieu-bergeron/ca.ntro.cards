package ca.ntro.core.reflection.object_graph.revisions;

import ca.ntro.core.path.ValuePath;

public class DeleteNtro 

       extends RevisionNtro
       
       implements Delete {
	
	public DeleteNtro() {
		super();
	}

	public DeleteNtro(String name) {
		super(name);
	}

	@Override
	public boolean isUpdate() {
		return false;
	}

	@Override
	public boolean isDelete() {
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
		return "delete";
	}

}
