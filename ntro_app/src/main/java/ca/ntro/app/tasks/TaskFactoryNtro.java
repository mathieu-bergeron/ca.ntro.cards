package ca.ntro.app.tasks;

import java.util.HashMap;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.generic_task_graph.SimpleTaskOptions;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskGraphNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;

public abstract class TaskFactoryNtro<TASKS extends Tasks> 
 
       implements     ContainerTasks<TASKS>,
       				  OrphanTasks,
                      TasksExecutor {
	
	private ContainerTasksNtro<TASKS> tasks = newContainerTasksNtro();
	private TaskGraphTrace trace;

	public ContainerTasksNtro<TASKS> getTasks() {
		return tasks;
	}

	public void setTasks(ContainerTasksNtro<TASKS> tasks) {
		this.tasks = tasks;
	}

	public TaskGraphTrace getTrace() {
		return trace;
	}

	public void setTrace(TaskGraphTrace trace) {
		this.trace = trace;
	}
	
	

	public TaskFactoryNtro() {
		getTasks().setOrphanTasks(new HashMap<>());
		getTasks().setGraph(TaskGraph.newGraph());
		getTasks().setParent(getTasks().getGraph());
	}
	
	protected abstract String graphName();
	protected abstract ContainerTasksNtro<TASKS> newContainerTasksNtro();

	public void prepareToExecuteTasks() {
		if(getTrace() == null) {
			
			getTasks().getGraph().setGraphName(graphName());
			
			setTrace(getTasks().getGraph().newTrace());
		}
	}

	public void writeGraph() {
		getTasks().getGraph().write(Ntro.graphWriter());
	}
	
	@Override
	public void executeTasks() {
		if(getTrace() == null) {
			Ntro.exceptionService().throwException(new RuntimeException("Must first prepare all task graphs"));
		}
		
		getTrace().execute();
	}

	@Override
	public SimpleTaskCreator<?> orphanTask(String taskId, SimpleTaskOptions<?> options) {
		return orphanTask(new SimpleTaskDescriptorNtro<>(taskId), options);
	}

	@Override
	public <O> SimpleTaskCreator<O> orphanTask(SimpleTaskDescriptor<O> descriptor, SimpleTaskOptions options) {

		Task task = ((TaskGraphNtro) getTasks().getGraph()).newTaskInstance(descriptor.id(), options);
		
		getTasks().getOrphanTasks().put(task.id(), task);

		return new SimpleTaskCreatorNtro<>(getTasks().getOrphanTasks(), getTasks().getGraph(), getTasks().getParent(), task);
	}

	@Override
	public SimpleTaskCreator<?> task(String taskId) {
		return getTasks().task(taskId);
	}

	@Override
	public <O> SimpleTaskCreator<O> task(SimpleTaskDescriptor<O> descriptor) {
		return getTasks().task(descriptor);
	}

	@Override
	public TaskGroupCreator<?, TASKS> taskGroup(String taskGroupId) {
		return getTasks().taskGroup(taskGroupId);
	}

	@Override
	public <O> TaskGroupCreator<O, TASKS> taskGroup(TaskGroupDescriptor<O> descriptor) {
		return (TaskGroupCreator<O, TASKS>) getTasks().taskGroup(descriptor);
	}

}
