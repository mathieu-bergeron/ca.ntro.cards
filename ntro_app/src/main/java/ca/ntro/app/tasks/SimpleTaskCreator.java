package ca.ntro.app.tasks;

import ca.ntro.core.task_graphs.task_graph.Task;

public interface SimpleTaskCreator<O>


       extends TaskCreator<O, Task>,
               WaitsFor<O,
                        SimpleTaskDescriptor<?>,
                        SimpleTaskCreator<O>,
                        Task> {

	SimpleTaskCreator<O> executes(BlockingFrontendExecutor executor);

	SimpleTaskCreator<O> thenExecutes(BlockingFrontendExecutor executor);

	SimpleTaskCreator<O> thenExecutesAndReturnsValue(TypedBlockingFrontendExecutor<?> executor);

	SimpleTaskCreator<O> executesAndReturnsCreatedValue(TypedBlockingFrontendExecutor<?> executor);

	SimpleTaskCreator<O> andLaterExecutes(BlockingFrontendExecutor executor);
	

}
