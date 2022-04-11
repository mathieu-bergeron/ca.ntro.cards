package ca.ntro.core.reflection.object_graph.revisions;

import ca.ntro.core.stream.Stream;

public class ClearNtro 

       extends RevisionNtro 
       
       implements Clear {

	@Override
	public boolean isUpdate() {
		return false;
	}

	@Override
	public boolean isDelete() {
		return false;
	}

	@Override
	public boolean isInsert() {
		return false;
	}

	@Override
	public boolean isClear() {
		return true;
	}

	@Override
	public String revisionType() {
		return "clear";
	}

}
