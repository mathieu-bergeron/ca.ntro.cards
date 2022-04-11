package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.reflection.object_graph.revisions.Revision;
import ca.ntro.core.reflection.object_graph.revisions.Revisions;

public interface GenericObjectNode {

	Class<?> type();
	Object object();

	boolean graphEquals(ObjectNode otherNode);

	Revisions revisionsTo(ObjectNode target);
	void applyRevision(Revision revision);

}
