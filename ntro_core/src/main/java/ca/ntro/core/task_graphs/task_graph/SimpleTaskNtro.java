package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericSimpleTaskNtro;

public class      SimpleTaskNtro 

       extends    GenericSimpleTaskNtro<Task, 
                                        SimpleTask, 
                                        ExecutableTask, 
                                        TaskGroup, 
                                        TaskGraph> 

       implements SimpleTask {

	public synchronized void registerNewResult(Object result) {
		
	}


}
