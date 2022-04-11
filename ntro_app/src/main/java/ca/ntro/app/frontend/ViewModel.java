package ca.ntro.app.frontend;

import ca.ntro.core.reflection.object_graph.revisions.Revisions;

public interface ViewModel<VN extends Object> extends View<VN> {

	void displayModelUpdates(Revisions updates);

}
