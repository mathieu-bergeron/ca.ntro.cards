package ca.ntro.cards.common.test_cases.execution_trace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.common.messages.MsgStopExecutionReplay;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.core.initialization.Ntro;

public class ExecutionTraceFull<EXECUTABLE_MODEL extends CommonExecutableModel,
                                DASHBOARD_MODEL  extends CommonDashboardModel> 

       implements ExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> {

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

	private boolean isValidIndex(int index) {
		return index >= 0 && index < trace.size();
	}
	
	public void stepForward() {
		if(isValidIndex(current + 1)) {

			current++;

		}else {

			MsgStopExecutionReplay msgStopCodeExecution = NtroApp.newMessage(MsgStopExecutionReplay.class);
			msgStopCodeExecution.send();
		}
	}

	public void stepBackward() {
		if(isValidIndex(current - 1)) {
			
			current--;
			
		}else {

			MsgStopExecutionReplay msgStopCodeExecution = NtroApp.newMessage(MsgStopExecutionReplay.class);
			msgStopCodeExecution.send();

		}
	}

	@Override
	public EXECUTABLE_MODEL firstModel() {
		return trace.get(0);
	}

	@Override
	public void copyDataTo(EXECUTABLE_MODEL model) {
		model.copyDataFrom(trace.get(current));
	}

	@Override
	public void updateDashboardModel(DASHBOARD_MODEL dashboardModel) {
		dashboardModel.setCurrentStep(current+1);
		dashboardModel.setNumberOfSteps(trace.size());
	}
}
