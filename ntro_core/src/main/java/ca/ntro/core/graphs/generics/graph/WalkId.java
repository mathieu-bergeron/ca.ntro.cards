package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.path.Path;

public interface WalkId extends GenericWalk<EdgeType, WalkId> {
	
	Path toPath();
	
	static WalkId fromPath(Path path) {
		WalkIdNtro walkId = new WalkIdNtro();

		for(int i = 0; i < path.nameCount(); i++) {
			walkId.add(new EdgeTypeNtro(Direction.FORWARD, path.name(i)));
		}

		return walkId;
	}

}
