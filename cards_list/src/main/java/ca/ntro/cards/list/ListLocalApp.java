package ca.ntro.cards.list;

import ca.ntro.app.NtroClientFx;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;

public class ListLocalApp implements NtroClientFx {

	@Override
	public void registerModels(ModelRegistrar registrar) {
		ListRegistrar.registerModels(registrar);
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {
		
	}

	@Override
	public void registerFrontend(FrontendRegistrarFx registrar) {
		
	}

	@Override
	public void registerBackend(BackendRegistrar registrar) {
		
	}

}
