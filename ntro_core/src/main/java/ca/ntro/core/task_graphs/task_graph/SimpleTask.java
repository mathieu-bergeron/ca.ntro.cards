package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericSimpleTask;

public interface SimpleTask extends Task, 
                                    GenericSimpleTask<Task, 
                                                      SimpleTask, 
                                                      ExecutableTask, 
                                                      TaskGroup, 
                                                      TaskGraph> {


}
