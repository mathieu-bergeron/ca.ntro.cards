package ca.ntro.cards.common.test_cases.indexing;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.enums.Mode;
import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTrace;
import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTraceFull;
import ca.ntro.core.initialization.Ntro;

public class ExecutionTraceByMode<EXECUTABLE_MODEL extends CommonExecutableModel,
                                  DASHBOARD_MODEL  extends CommonDashboardModel> 

       implements Value, Serializable {
	
	
	private Class<? extends CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL>> executionTraceClass;
	
	private Map<String, CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL>> traceByMode = new HashMap<>();

	public Map<String, CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL>> getTraceByMode() {
		return traceByMode;
	}

	public void setTraceByMode(Map<String, CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL>> traceByMode) {
		this.traceByMode = traceByMode;
	}

	public void registerExecutionTraceClass(Class<? extends CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL>> executionTraceClass) {
		this.executionTraceClass = executionTraceClass;
	}

	public void pushReference(Mode mode, EXECUTABLE_MODEL snapshot) {
		CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> trace = traceByMode.get(mode.name());

		if(trace == null) {
			trace = Ntro.factory().newInstance(executionTraceClass);
			traceByMode.put(mode.name(), trace);
		}

		trace.pushReferenceTo(snapshot);
	}

	public CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> trace(Mode mode) {
		return traceByMode.get(mode.name());
	}

}
