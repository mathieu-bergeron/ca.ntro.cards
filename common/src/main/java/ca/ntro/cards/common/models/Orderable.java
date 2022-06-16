package ca.ntro.cards.common.models;

public interface Orderable<O extends Object> {

	boolean equalTo(O other);
	
	boolean smallerThan(O other);
	boolean smallerThanOrEqualTo(O other);

	boolean biggerThan(O other);
	boolean biggerThanOrEqualTo(O other);

}
