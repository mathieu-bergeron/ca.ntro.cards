package ca.ntro.cards.common.models;

import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.cards.common.backend.model_history.ModelHistory;
import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.cards.common.models.world2d.CommonObject2d;
import ca.ntro.cards.common.models.world2d.CommonWorld2d;

public abstract class CommonCardsModel<CARDS_MODEL extends CommonCardsModel,
                                       OBJECT2D    extends CommonObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                       WORLD2D     extends CommonWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                       OPTIONS     extends CommonDrawingOptions,
                                       VIEW_DATA   extends CommonViewData<OBJECT2D, WORLD2D, OPTIONS>>



               extends CommonCanvasModel<CARDS_MODEL, OBJECT2D, WORLD2D, OPTIONS, VIEW_DATA> {

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
