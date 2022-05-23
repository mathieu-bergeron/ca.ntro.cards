package ca.ntro.cards.common.models.values.execution_trace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.core.initialization.Ntro;

public class ExecutionTraceFull<EXECUTABLE_MODEL extends CommonExecutableModel> 

       implements ExecutionTrace<EXECUTABLE_MODEL> {

	private List<EXECUTABLE_MODEL> trace = Collections.synchronizedList(new ArrayList<>());
	private int current = 0;

	public List<EXECUTABLE_MODEL> getTrace() {
		return trace;
	}

	public void setTrace(List<EXECUTABLE_MODEL> trace) {
		this.trace = trace;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	@Override
	public void pushReferenceTo(EXECUTABLE_MODEL model) {
		trace.add(model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pushCopyOf(EXECUTABLE_MODEL model) {
		trace.add((EXECUTABLE_MODEL) Ntro.reflection().clone(model));
	}
	
	public EXECUTABLE_MODEL currentModel() {
		EXECUTABLE_MODEL currentModel = null;
		
		if(isValidIndex()) {
			currentModel = trace.get(current);
		}
		
		return currentModel;
	}

	private boolean isValidIndex() {
		return current >= 0 && current < trace.size();
	}
	
	public void stepForward() {
		current++;
		
		if(current >= trace.size()) {
			current = trace.size() - 1;
		}
	}

	public void stepBackward() {
		current--;
		
		if(current < 0) {
			current = 0;
		}
	}

	@Override
	public void updateDashboard(CommonDashboardModel dashboardModel) {
		dashboardModel.setNumberOfSteps(trace.size());
		dashboardModel.setCurrentStep(current);
	}
}
