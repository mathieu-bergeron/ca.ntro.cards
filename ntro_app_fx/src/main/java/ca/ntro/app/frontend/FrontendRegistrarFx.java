package ca.ntro.app.frontend;

import ca.ntro.app.services.Window;

public class      FrontendRegistrarFx 

       extends    FrontendRegistrarNtro<ViewRegistrarFx>

       implements FrontendRegistrar<ViewRegistrarFx> {


	@Override
	protected ViewRegistrarFx newViewRegistrarInstance() {
		return new ViewRegistrarFx();
	}

	@Override
	protected Frontend<ViewRegistrarFx> defaultFrontend(Window window) {
		return new DefaultFrontendFx(window);
	}


}
