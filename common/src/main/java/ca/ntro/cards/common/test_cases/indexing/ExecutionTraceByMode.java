package ca.ntro.cards.common.test_cases.indexing;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.enums.Attempt;
import ca.ntro.cards.common.test_cases.descriptor.CommonAttemptDescriptor;
import ca.ntro.cards.common.test_cases.descriptor.CommonTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTrace;
import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTraceFull;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

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

	public void pushReference(Attempt mode, EXECUTABLE_MODEL snapshot) {
		CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> trace = traceByMode.get(mode.name());

		if(trace == null) {
			trace = Ntro.factory().newInstance(executionTraceClass);
			traceByMode.put(mode.name(), trace);
		}

		trace.pushReferenceTo(snapshot);
	}

	public CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> trace(Attempt mode) {
		return traceByMode.get(mode.name());
	}
	
	public Stream<CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL>> stream(){
		return new StreamNtro<>() {

			@Override
			public void forEach_(Visitor<CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL>> visitor) throws Throwable {
				for(CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> trace : traceByMode.values()) {
					visitor.visit(trace);
				}
			}
		};
	}

	public void addAttempDescriptors(CommonTestCaseDescriptor testCaseDescriptor) {
		for(Map.Entry<String, CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL>> entry : traceByMode.entrySet()) {

			Attempt attempt = Attempt.valueOf(entry.getKey());
			CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> trace = entry.getValue();
			
			testCaseDescriptor.addAttemptDescriptor(attempt, trace.asAttemptDescriptor());
		}
	}

}
