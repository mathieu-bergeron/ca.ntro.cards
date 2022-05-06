package ca.ntro.cards.models;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.Watchable;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.models.values.Card;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_graph.Initializable;
import ca.ntro.core.stream.Stream;

public abstract class CardsModel implements Model, Watchable, Initializable {
	
	public static long biggestId;
	private long version;

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
			incrementVersion();
		}
	}

	protected abstract void createFirstVersion();

	protected abstract Card cardById(String cardId);
	
	protected abstract Stream<Card> cards();

	
	@Override
	public void initialize() {
		Set<String> existingIds = new HashSet<>();
		Set<Card> cardsNeedingId = new HashSet<>();
		
		cards().forEach(c -> {
			if(c.getId() == -1) {

				cardsNeedingId.add(c);

			}else if(existingIds.contains(c.id())) {

				throw new IdNotUniqueException(c.id());

			}else {

				updateBiggestId(c);
				existingIds.add(c.id());
			}
		});
		
		for(Card card : cardsNeedingId) {
			card.setId(nextId());
		}
	}

	private void updateBiggestId(Card card) {
		if(card.getId() > biggestId) {
			biggestId = card.getId();
		}
	}
	
	public void addCard(Card card) {
		if(cardById(card.id()) != null) {
			Ntro.throwException(new IdNotUniqueException(card.id()));
		}
		
		updateBiggestId(card);

		addCardImpl(card);
	}
	
	protected abstract void addCardImpl(Card card);

	public static long nextId() {
		biggestId++;
		long nextId = biggestId;
		return nextId;
	}

	public void updateViewData(CardsViewData cardsViewData) {
		cardsViewData.removeCardsNotIn(cards());
		
		updateViewDataImpl(cardsViewData);
	}
	
	protected abstract void updateViewDataImpl(CardsViewData cardsViewData);
		

}
