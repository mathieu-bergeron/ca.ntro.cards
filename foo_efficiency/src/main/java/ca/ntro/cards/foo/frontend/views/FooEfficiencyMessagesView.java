package ca.ntro.cards.foo.frontend.views;

import ca.ntro.cards.efficiency.frontend.views.EfficiencyMessagesView;
import ca.ntro.core.stream.Stream;
import javafx.scene.layout.Pane;

public class FooEfficiencyMessagesView extends EfficiencyMessagesView {

	@Override
	protected Stream<Pane> spaces() {
		return null;
	}

	@Override
	protected Pane messagesContainer() {
		return null;
	}

}
