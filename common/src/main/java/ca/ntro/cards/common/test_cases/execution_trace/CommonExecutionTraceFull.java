package ca.ntro.cards.common.test_cases.execution_trace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.common.messages.MsgStopExecutionReplay;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.core.initialization.Ntro;

public abstract class CommonExecutionTraceFull<EXECUTABLE_MODEL extends CommonExecutableModel,
                                               DASHBOARD_MODEL  extends CommonDashboardModel> 

       implements CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> {

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
	public void pushCloneOf(EXECUTABLE_MODEL model) {
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
	public int numberOfSteps() {
		return trace.size();
	}
}
