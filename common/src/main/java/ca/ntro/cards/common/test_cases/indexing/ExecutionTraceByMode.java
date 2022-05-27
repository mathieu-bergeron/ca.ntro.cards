package ca.ntro.cards.common.test_cases.indexing;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.enums.Mode;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTrace;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceFull;

public class ExecutionTraceByMode<EXECUTABLE_MODEL extends CommonExecutableModel,
                                  DASHBOARD_MODEL  extends CommonDashboardModel> 

       implements Value, Serializable {
	
	
	private Map<String, ExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL>> traceByMode = new HashMap<>();

	public Map<String, ExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL>> getTraceByMode() {
		return traceByMode;
	}

	public void setTraceByMode(Map<String, ExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL>> traceByMode) {
		this.traceByMode = traceByMode;
	}

	public void pushReference(Mode mode, EXECUTABLE_MODEL snapshot) {
		ExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> trace = traceByMode.get(mode.name());

		if(trace == null) {
			trace = new ExecutionTraceFull<>();
			traceByMode.put(mode.name(), trace);
		}

		trace.pushReferenceTo(snapshot);
	}

	public ExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> executionTraceByMode(Mode mode) {
		return traceByMode.get(mode.name());
	}

}
