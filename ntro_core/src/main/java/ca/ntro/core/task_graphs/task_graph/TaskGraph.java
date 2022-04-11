package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraph;
import ca.ntro.core.task_graphs.generic_task_graph.SimpleTaskOptions;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGroupOptions;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTraceNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskResultsCondition;

public interface TaskGraph extends GenericTaskGraph<Task, 
                                                      SimpleTask, 
                                                      ExecutableTask, 
                                                      TaskGroup, 
                                                      TaskGraph>,
                                    TaskContainer {

	public static TaskGraph newGraph() {
		
		TaskGraphNtro graph = new TaskGraphNtro();
		
		
		graph.setDefaultSimpleTaskOptions(SimpleTaskOptions.taskClass(SimpleTaskNtro.class)
				                                           .traceClass(TaskTraceNtro.class)
				                                           .resultsClass(TaskResultsCondition.class));

		graph.setDefaultTaskGroupOptions(TaskGroupOptions.taskGroupClass(TaskGroupNtro.class));
		
		graph.initialize();
		
		return graph;
	}


}
