package ca.ntro.cards.playground;

import ca.ntro.app.NtroClientFx;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.playground.backend.PlaygroundBackend;
import ca.ntro.cards.playground.frontend.PlaygroundFrontend;

public class PlaygroundLocalApp implements NtroClientFx {
	
	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	public void registerModels(ModelRegistrar registrar) {
		PlaygroundRegistrar.registerModels(registrar);
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {
		PlaygroundRegistrar.registerMessages(registrar);
	}

	@Override
	public void registerFrontend(FrontendRegistrarFx registrar) {
		registrar.registerFrontend(new PlaygroundFrontend());
	}

	@Override
	public void registerBackend(BackendRegistrar registrar) {
		registrar.registerBackend(new PlaygroundBackend());
	}

}
