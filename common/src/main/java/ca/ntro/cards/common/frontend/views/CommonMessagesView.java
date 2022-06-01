package ca.ntro.cards.common.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.common.frontend.events.EvtHideMessages;
import ca.ntro.core.stream.Stream;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class CommonMessagesView extends ViewFx {
	
	protected abstract Stream<Pane> spaces();

	protected abstract Pane messagesContainer();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initializeSpaces();
	}

	private void initializeSpaces() {
		EvtHideMessages evtHideMessages = NtroApp.newEvent(EvtHideMessages.class);
		
		if(spaces() != null) {
			spaces().forEach(space -> {
				space.addEventFilter(MouseEvent.MOUSE_CLICKED, evtFx -> {
					evtHideMessages.trigger();
				});
			});
		}
	}

}
