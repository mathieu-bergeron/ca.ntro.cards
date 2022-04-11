package ca.ntro.app.tasks.system;

import java.util.Map;

import ca.ntro.app.tasks.SimpleTaskCreatorNtro;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskContainer;

public class   SystemSimpleTaskCreatorNtro<O> 

       extends     SimpleTaskCreatorNtro<O> 

       implements  SystemSimpleTaskCreator<O> {


	public SystemSimpleTaskCreatorNtro(Map<String, Task> orphanTasks, 
			                            TaskGraph graph, TaskContainer parent, 
			                            Task task) {

		super(orphanTasks, graph, parent, task);
	}
	
	

}
