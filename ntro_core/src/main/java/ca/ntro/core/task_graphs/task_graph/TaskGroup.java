package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGroup;

public interface TaskGroup extends Task, 
							       TaskContainer,
                                   GenericTaskGroup<Task, 
                                                    SimpleTask, 
                                                    ExecutableTask, 
                                                    TaskGroup, 
                                                    TaskGraph> {

}
