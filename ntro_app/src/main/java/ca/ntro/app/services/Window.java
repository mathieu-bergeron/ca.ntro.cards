package ca.ntro.app.services;

import ca.ntro.app.frontend.View;

public interface Window {

	void resize(int width, int height);
	void installRootView(View<?> rootView);
	void show();
	void hide();
	void fullscreen(boolean isFullscreen);
	void decorations(boolean hasDecorations);

}
