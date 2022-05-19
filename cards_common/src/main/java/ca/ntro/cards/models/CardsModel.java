package ca.ntro.cards.models;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.Watchable;
import ca.ntro.cards.backend.model_history.ModelHistory;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.models.identifyers.IdFactory;
import ca.ntro.cards.models.identifyers.IdNotUniqueException;
import ca.ntro.cards.models.values.Card;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_graph.Initializable;
import ca.ntro.core.stream.Stream;
import javafx.application.Platform;

public abstract class CardsModel<CARDS_MODEL extends CardsModel<CARDS_MODEL>> 

       implements     Model, Watchable, Initializable {
	
	private long version;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	private ReentrantLock lock;
	private ModelHistory<CARDS_MODEL> modelHistory;
	
	public void registerLock(ReentrantLock lock) {
		this.lock = lock;
	}

	public void registerModelHistory(ModelHistory<CARDS_MODEL> modelHistory) {
		this.modelHistory = modelHistory;
	}
	
	@SuppressWarnings("unchecked")
	protected void saveStep() {
		// XXX: only excute if we are
		//      not locked by JavaFX GUI Thread
		lock.lock();
		lock.unlock();

		modelHistory.pushCopyOf((CARDS_MODEL) this);

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
	
	public abstract void run();

		

}
