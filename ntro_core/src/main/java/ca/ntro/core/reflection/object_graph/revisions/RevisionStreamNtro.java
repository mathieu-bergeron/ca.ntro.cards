package ca.ntro.core.reflection.object_graph.revisions;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class RevisionStreamNtro 

       extends StreamNtro<Revision> {

	
	private List<Revision> revisions = new ArrayList<>();

	public List<Revision> getRevisions() {
		return revisions;
	}

	public void setRevisions(List<Revision> revisions) {
		this.revisions = revisions;
	}
	
	
	public void add(Revision revision) {
		getRevisions().add(revision);
	}
	
	public void addAll(Stream<Revision> revisions) {
		revisions.forEach(revision -> {
			getRevisions().add(revision);
		});
	}
	




	@Override
	public void forEach_(Visitor<Revision> visitor) throws Throwable {
		for(Revision revision : revisions) {
			visitor.visit(revision);
		}
	}
	

}
