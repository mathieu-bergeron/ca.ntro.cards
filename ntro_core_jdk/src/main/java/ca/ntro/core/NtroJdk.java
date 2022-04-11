package ca.ntro.core;

import ca.ntro.core.initialization.Initializer;
import ca.ntro.core.initialization.InitializerJdk;

public class NtroJdk {

	public static Initializer initializer() {
		return new InitializerJdk();
	}
}
