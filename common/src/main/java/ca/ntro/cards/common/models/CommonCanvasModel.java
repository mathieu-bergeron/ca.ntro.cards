package ca.ntro.cards.common.models;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.Watch;
import ca.ntro.app.models.WriteObjectGraph;
import ca.ntro.cards.common.backend.model_history.ModelHistory;
import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.common.models.identifyers.IdFactory;
import ca.ntro.cards.common.models.identifyers.IdNotUniqueException;
import ca.ntro.cards.common.models.values.Card;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.cards.common.models.world2d.CommonObject2d;
import ca.ntro.cards.common.models.world2d.CommonWorld2d;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_graph.Initialize;
import ca.ntro.core.stream.Stream;

public abstract class CommonCanvasModel<CARDS_MODEL extends CommonCanvasModel,
                                       OBJECT2D    extends CommonObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                       WORLD2D     extends CommonWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                       OPTIONS     extends CommonDrawingOptions,
                                       VIEW_DATA   extends CommonViewData<OBJECT2D, WORLD2D, OPTIONS>>

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
	
	
	private ReentrantLock lock;
	private ModelHistory<CARDS_MODEL> modelHistory;
	
	public void registerLock(ReentrantLock lock) {
		this.lock = lock;
	}

	public void registerModelHistory(ModelHistory<CARDS_MODEL> modelHistory) {
		this.modelHistory = modelHistory;
	}
	
	@SuppressWarnings("unchecked")
	protected void signalerEtape() {
		// XXX: only excute if we are
		//      not locked by JavaFX GUI Thread
		lock.lock();
		lock.unlock();

		modelHistory.pushCopyOf((CARDS_MODEL) this);

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


	public void updateViewData(VIEW_DATA viewData) {

		viewData.removeCardsNotIn(cards());
		
		updateViewDataImpl(viewData);
	}
	
	protected abstract void updateViewDataImpl(VIEW_DATA cardsViewData);

	
	public abstract void copyDataFrom(CARDS_MODEL cardsModel);

	
	public abstract void run();

}
