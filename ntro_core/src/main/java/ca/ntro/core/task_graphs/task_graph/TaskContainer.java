package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskContainer;

public interface TaskContainer extends GenericTaskContainer<Task, 
                                                            SimpleTask, 
                                                            ExecutableTask, 
                                                            TaskGroup, 
                                                            TaskGraph> {

}
