package common.models;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.Watch;
import ca.ntro.app.models.WriteObjectGraph;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_graph.Initialize;
import ca.ntro.core.stream.Stream;
import common.frontend.views.data.CardsViewData;
import common.models.identifyers.IdFactory;
import common.models.identifyers.IdNotUniqueException;
import common.models.values.Card;

public abstract class CardsModel<CARDS_MODEL extends CardsModel<CARDS_MODEL>> 

       implements     Model, Watch, Initialize, WriteObjectGraph {

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
		if(getVersion() == 0) {
			createFirstVersion();
			incrementVersion();
		}
	}

	public abstract void createFirstVersion();

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

	
	public abstract void copyDataFrom(CARDS_MODEL cardsModel);


}
