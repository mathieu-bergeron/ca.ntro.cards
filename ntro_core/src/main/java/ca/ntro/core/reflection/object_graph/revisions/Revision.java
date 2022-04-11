package ca.ntro.core.reflection.object_graph.revisions;


public interface Revision {
	
	boolean isUpdate();
	Update asUpdate();

	boolean isDelete();
	Delete asDelete();

	boolean isInsert();
	Insert asInsert();
	
	boolean isClear();
	Clear asClear();

	String name();
	boolean targets(String name);
	
	Revisions subRevisions();

}
