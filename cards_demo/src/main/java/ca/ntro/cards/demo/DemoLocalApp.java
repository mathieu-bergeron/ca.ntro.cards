package ca.ntro.cards.demo;

import ca.ntro.app.NtroClientFx;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.demo.backend.DemoBackend;
import ca.ntro.cards.demo.frontend.DemoFrontend;

public class DemoLocalApp implements NtroClientFx {
	
	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	public void registerModels(ModelRegistrar registrar) {
		DemoRegistrar.registerModels(registrar);
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {
		DemoRegistrar.registerMessages(registrar);
	}

	@Override
	public void registerFrontend(FrontendRegistrarFx registrar) {
		registrar.registerFrontend(new DemoFrontend());
	}

	@Override
	public void registerBackend(BackendRegistrar registrar) {
		registrar.registerBackend(new DemoBackend());
	}

}
