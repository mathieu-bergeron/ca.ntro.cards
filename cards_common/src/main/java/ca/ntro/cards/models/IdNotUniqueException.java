package ca.ntro.cards.models;

public class IdNotUniqueException extends Exception {

	public IdNotUniqueException(String id) {
		System.out.println("Id not unique: " + id);
	}

	private static final long serialVersionUID = -1091052493518895014L;

}
