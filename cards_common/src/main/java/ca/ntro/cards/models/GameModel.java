package ca.ntro.cards.models;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.Watchable;
import ca.ntro.cards.frontend.views.data.GameViewData;
import ca.ntro.cards.models.values.Card;

public abstract class GameModel implements Model, Watchable {

	private long version = 0;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	protected void incrementVersion() {
		version++;
	}
	
	public void flipCard(String cardId) {
		Card card = cardById(cardId);
		
		if(card != null) {
			card.flip();
		}
	}
	
	public void createFirstVersionIfNeeded() {
		if(version == 0) {
			createFirstVersion();
		}
	}


	protected abstract void createFirstVersion();

	protected abstract Card cardById(String cardId);

	public abstract void updateViewData(GameViewData gameViewData);

}
