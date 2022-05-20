package ca.ntro.cards.models;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.WriteObjectGraph;
import ca.ntro.app.models.Watch;
import ca.ntro.cards.backend.model_history.ModelHistory;
import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.common.models.CardsModel;
import ca.ntro.cards.common.models.identifyers.IdFactory;
import ca.ntro.cards.common.models.identifyers.IdNotUniqueException;
import ca.ntro.cards.common.models.values.Card;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.object_graph.Initialize;
import ca.ntro.core.stream.Stream;

public abstract class ProcedureCardsModel<CARDS_MODEL extends ProcedureCardsModel<CARDS_MODEL>> 

       extends CardsModel<CARDS_MODEL>

       implements     Model, Watch, Initialize, WriteObjectGraph {
	
	
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
	
	public abstract void run();

		

}
