package ca.ntro.cards.models;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.Watchable;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.models.identifyers.IdFactory;
import ca.ntro.cards.models.identifyers.IdNotUniqueException;
import ca.ntro.cards.models.values.Card;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_graph.Initializable;
import ca.ntro.core.stream.Stream;

public abstract class CardsModel implements Model, Watchable, Initializable {
	
	private long version;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	
	protected void registerSimpleOperation() {
		System.out.println("registerSimpleOperation");
		System.out.flush();
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
			if(c == null) {
				return;
			}

			if(c.getId() == -1) {

				cardsNeedingId.add(c);

			}else if(existingIds.contains(c.id())) {

				throw new IdNotUniqueException(c.id());

			}else {

				IdFactory.registerId(c.getId());
				existingIds.add(c.id());

			}
		});
		
		for(Card card : cardsNeedingId) {
			card.setId(IdFactory.nextId());
		}
	}

	
	public void addCard(Card card) {
		if(cardById(card.id()) != null) {
			Ntro.throwException(new IdNotUniqueException(card.id()));
		}
		
		IdFactory.registerId(card.getId());

		addCardImpl(card);
	}
	
	protected abstract void addCardImpl(Card card);


	public void updateViewData(CardsViewData cardsViewData) {
		cardsViewData.removeCardsNotIn(cards());
		
		updateViewDataImpl(cardsViewData);
	}
	
	protected abstract void updateViewDataImpl(CardsViewData cardsViewData);
	
		

}
