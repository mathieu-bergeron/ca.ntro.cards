package ca.ntro.cards.foo;

import ca.ntro.app.NtroClientFx;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.foo.backend.FooBackend;
import ca.ntro.cards.foo.frontend.FooFrontend;

public class FooLocalApp implements NtroClientFx {
	
	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	public void registerModels(ModelRegistrar registrar) {
		FooRegistrar.registerModels(registrar);
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {
		FooRegistrar.registerMessages(registrar);
	}

	@Override
	public void registerFrontend(FrontendRegistrarFx registrar) {
		registrar.registerFrontend(new FooFrontend());
	}

	@Override
	public void registerBackend(BackendRegistrar registrar) {
		registrar.registerBackend(new FooBackend());
	}

}
