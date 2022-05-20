package common.models.identifyers;

public class IdFactory {

	private static long biggestId;

	public static long nextId() {
		biggestId++;
		return biggestId;
	}

	public static void registerId(long id) {
		if(id > biggestId) {
			biggestId = id;
		}
	}

}
