package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.handlers.CancelHandler;
import ca.ntro.core.task_graphs.handlers.ExecuteHandler;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public abstract class GenericExecutableTaskNtro<T  extends GenericTask<T,ST,ET,TG,G>,
                                                ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                                ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                                TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                                G  extends GenericTaskGraph<T,ST,ET,TG,G>> 

       extends        GenericSimpleTaskNtro<T,ST,ET,TG,G>

       implements     GenericExecutableTask<T,ST,ET,TG,G> {
	
	
	private ExecuteHandler executeHandler;
	private CancelHandler cancelHandler;
	private ExceptionHandler exceptionHandler;

	public ExecuteHandler getExecuteHandler() {
		return executeHandler;
	}

	public void setExecuteHandler(ExecuteHandler executeHandler) {
		this.executeHandler = executeHandler;
	}

	public CancelHandler getCancelHandler() {
		return cancelHandler;
	}

	public void setCancelHandler(CancelHandler cancelHandler) {
		this.cancelHandler = cancelHandler;
	}

	public ExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}

	public void setExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	@Override
	public boolean isExecutableTask() {
		return true;
	}

	@Override
	public void execute(ExecuteHandler executeHandler) {
		setExecuteHandler(executeHandler);
	}

	@Override
	public void cancel(CancelHandler cancelHandler) {
		setCancelHandler(cancelHandler);
	}

	@Override
	public void handleException(ExceptionHandler exceptionHandler) {
		setExceptionHandler(exceptionHandler);
	}

}
