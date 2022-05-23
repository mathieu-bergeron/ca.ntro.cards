package ca.ntro.cards.common.models;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.models.Watch;
import ca.ntro.app.models.WriteObjectGraph;
import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.common.models.values.execution_trace.ExecutionTrace;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.cards.common.models.world2d.CommonObject2d;
import ca.ntro.cards.common.models.world2d.CommonWorld2d;

public abstract class CommonExecutableModel<CARDS_MODEL extends CommonExecutableModel,
                                       OBJECT2D    extends CommonObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                       WORLD2D     extends CommonWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                       OPTIONS     extends CommonDrawingOptions,
                                       VIEW_DATA   extends CommonViewData<OBJECT2D, WORLD2D, OPTIONS>>



               extends CommonCanvasModel<CARDS_MODEL, OBJECT2D, WORLD2D, OPTIONS, VIEW_DATA> 

               implements Watch, WriteObjectGraph, Serializable {

	private ReentrantLock lock;
	private ExecutionTrace<CARDS_MODEL> modelHistory;
	
	public void registerLock(ReentrantLock lock) {
		this.lock = lock;
	}

	public void registerModelHistory(ExecutionTrace<CARDS_MODEL> modelHistory) {
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

	public abstract void generateTestCase(TestCaseDescriptor descriptor);

	protected abstract int testCaseSize();

	public abstract void onBeforeRunning();
	public abstract void run();
	public abstract void onAfterRunning();

}
