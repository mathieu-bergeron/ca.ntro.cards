package ca.ntro.cards.common.models;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;

import ca.ntro.app.models.Watch;
import ca.ntro.app.models.WriteObjectGraph;
import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.cards.common.models.world2d.CommonObject2d;
import ca.ntro.cards.common.models.world2d.CommonWorld2d;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTrace;

public abstract class CommonExecutableModel<CARDS_MODEL extends CommonExecutableModel,
                                            OBJECT2D    extends CommonObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                            WORLD2D     extends CommonWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                            OPTIONS     extends CommonDrawingOptions,
                                            VIEW_DATA   extends CommonViewData<OBJECT2D, WORLD2D, OPTIONS>>



               extends CommonCanvasModel<CARDS_MODEL, OBJECT2D, WORLD2D, OPTIONS, VIEW_DATA> 

               implements Watch, WriteObjectGraph, Serializable {

	private static final long serialVersionUID = -6280486621314092815L;

	public abstract void initializeAsTestCase(AbstractTestCaseDescriptor descriptor);

	public abstract int testCaseSize();

	public void onBeforeRunning() {}

	public abstract void run();

	public void onAfterRunning() {}

}
