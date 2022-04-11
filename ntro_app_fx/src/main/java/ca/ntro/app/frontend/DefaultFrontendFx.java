package ca.ntro.app.frontend;

import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.services.Window;
import ca.ntro.app.services.WindowNull;
import ca.ntro.app.tasks.frontend.FrontendTasks;

public class DefaultFrontendFx implements Frontend<ViewRegistrarFx> {
	
	private Window window = new WindowNull();

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public DefaultFrontendFx() {
	}

	public DefaultFrontendFx(Window window) {
		setWindow(window);
	}

	@Override
	public void registerEvents(EventRegistrar registrar) {
	}

	@Override
	public void registerViews(ViewRegistrarFx registrar) {
	}

	@Override
	public void createTasks(FrontendTasks tasks) {
	}

	@Override
	public void execute() {
		//getWindow().resize(600,400);
		//getWindow().show();
		System.out.println("[INFO] Ntro default frontend. Please register a frontend");
	}
}
