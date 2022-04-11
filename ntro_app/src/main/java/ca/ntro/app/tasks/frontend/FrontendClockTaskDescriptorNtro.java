package ca.ntro.app.tasks.frontend;

import ca.ntro.core.clock.TickNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.generic_task_graph.SimpleTaskOptions;
import ca.ntro.core.task_graphs.task_graph.ExecutableTask;
import ca.ntro.core.task_graphs.task_graph.ExecutableTaskNtro;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskContainer;
import ca.ntro.core.task_graphs.task_graph_trace.TaskResultsEventHandler;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTraceNtro;

public class FrontendClockTaskDescriptorNtro<O>

       extends FrontendSimpleTaskDescriptorNtro<O> {
	
	private long lastTick = Ntro.time().nowNanoseconds();

	public FrontendClockTaskDescriptorNtro(String id) {
		super(id);
	}

	@Override
	public Task newTask(TaskContainer graph) {

		ExecutableTask task = graph.newTask(getId(), 
				                            SimpleTaskOptions.taskClass(ExecutableTaskNtro.class) 
				                                             .traceClass(TaskTraceNtro.class)
				                                             .resultsClass(TaskResultsEventHandler.class));
		
		
		task.execute((inputs, notifyer) -> {
			
			Ntro.time().runRepeatedly(1, () -> {
				
				long tick = Ntro.time().nowNanoseconds();
				
				double elapsed = (tick - lastTick) / 1E9;
				
				lastTick = tick;
				
				notifyer.addResult(new TickNtro(elapsed));
			});
		});
		
		return task;
	}

}
