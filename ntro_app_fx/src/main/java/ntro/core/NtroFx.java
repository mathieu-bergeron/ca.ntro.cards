package ntro.core;

import ca.ntro.core.initialization.Initializer;


import ntro.core.initialization.InitializerFx;


public class NtroFx {

	public static Initializer initializer() {
		return new InitializerFx();
	}

}
