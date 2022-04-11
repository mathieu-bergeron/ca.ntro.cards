package ca.ntro.app.tasks;

import java.util.Map;

import ca.ntro.core.identifyers.Key;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.generic_task_graph.SimpleTaskOptions;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGroupOptions;
import ca.ntro.core.task_graphs.task_graph.ExecutableTask;
import ca.ntro.core.task_graphs.task_graph.ExecutableTaskNtro;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskContainer;
import ca.ntro.core.task_graphs.task_graph.TaskGroup;
import ca.ntro.core.task_graphs.task_graph.TaskGroupNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTraceNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskResultsCondition;

public abstract class ContainerTasksNtro<TASKS extends Tasks>


       implements     ContainerTasks<TASKS> {

	private Map<String, Task> orphanTasks;
	private TaskGraph graph;
	private TaskContainer parent;


	public Map<String, Task> getOrphanTasks() {
		return orphanTasks;
	}

	public void setOrphanTasks(Map<String, Task> orphanTasks) {
		this.orphanTasks = orphanTasks;
	}

	public TaskGraph getGraph() {
		return graph;
	}

	public void setGraph(TaskGraph graph) {
		this.graph = graph;
	}
	
	public TaskContainer getParent() {
		return parent;
	}

	public void setParent(TaskContainer parent) {
		this.parent = parent;
	}
	
	
	public ContainerTasksNtro() {
	}
	
	public ContainerTasksNtro(Map<String, Task> orphanTasks, TaskGraph graph, TaskContainer parent) {
		setOrphanTasks(orphanTasks);
		setGraph(graph);
		setParent(parent);
	}


	protected abstract <O> TaskGroupCreator<O, TASKS> newTaskGroupCreator(Map<String, Task> orphanTasks, 
			                                                          TaskGraph graph, 
			                                                          TaskContainer parent, 
			                                                          TaskGroup group); 

	protected <O> SimpleTaskCreator<O> newSimpleTaskCreator(Map<String, Task> orphanTasks, 
			                                                TaskGraph graph, 
			                                                TaskContainer parent, 
			                                                ExecutableTask task) {

		return new SimpleTaskCreatorNtro<>(orphanTasks, graph, parent, task);
	}

	@Override
	public SimpleTaskCreator<?> task(String taskId) {
		try {

			new Key(taskId);

		}catch(Throwable t) {

			Ntro.exceptionService().throwException(new RuntimeException("\n\n\n\t[FATAL] taskId must be a valid Java identifyer\n\t\t" + taskId + " is not valid\n\n\n"));
		}
		
		return task(new SimpleTaskDescriptorNtro<>(taskId));
	}

	@Override
	public <O> SimpleTaskCreator<O> task(SimpleTaskDescriptor<O> descriptor) {

		ExecutableTask task = getParent().newTask(descriptor.id(), 
				                                  SimpleTaskOptions.taskClass(ExecutableTaskNtro.class) 
				                                                   .traceClass(TaskTraceNtro.class)
				                                                   .resultsClass(TaskResultsCondition.class));

		return newSimpleTaskCreator(getOrphanTasks(), getGraph(), getParent(), task);
	}



	@Override
	public TaskGroupCreator<?, TASKS> taskGroup(String taskGroupId) {
		try {

			new Key(taskGroupId);

		}catch(Throwable t) {

			Ntro.exceptionService().throwException(new RuntimeException("\n\n\n\t[FATAL] taskGroupId must be a valid Java identifyer\n\t\t" + taskGroupId + " is not valid\n\n\n"));
		}
		
		return taskGroup(new TaskGroupDescriptorNtro(taskGroupId));
	}

	@Override
	public <O> TaskGroupCreator<O, TASKS> taskGroup(TaskGroupDescriptor<O> descriptor) {
		TaskGroup group = getParent().newGroup(descriptor.id(), TaskGroupOptions.taskGroupClass(TaskGroupNtro.class));
		
		return newTaskGroupCreator(getOrphanTasks(), getGraph(), getParent(), group);
	}

}
