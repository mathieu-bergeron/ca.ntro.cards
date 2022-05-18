package ca.ntro.cards.models;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.Watchable;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;
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

	@SuppressWarnings("rawtypes")
	private MsgRegisterSimpleOperation msgRegisterSimpleOperation;
	
	public void registerLock(ReentrantLock lock) {
		this.lock = lock;
	}

	@SuppressWarnings("rawtypes")
	public void registerMsgRegisterSimpleOperation(MsgRegisterSimpleOperation msgRegisterSimpleOperation) {
		this.msgRegisterSimpleOperation = msgRegisterSimpleOperation;
	}
	
	protected void registerSimpleOperation() {
		// XXX: waiting for JavaFx GUI thread to unlock us 
		lock.lock();
		lock.unlock();
		
		// XXX: JavaFx GUI thread 
		//      acquires the lock
		Platform.runLater(() -> {
			lock.lock();

			msgRegisterSimpleOperation.send();
		}); 

		// XXX: make sure JavaFX GUI thread
		//      has acquired the lock
		//      before returning
		while(!lock.isLocked()) {
			try {

				Thread.sleep(10);

			} catch (InterruptedException e) {

				Ntro.throwException(e);
			}
		}
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

	
	public abstract void copyDataFrom(CARDS_MODEL cardsModel);
	
		

}
