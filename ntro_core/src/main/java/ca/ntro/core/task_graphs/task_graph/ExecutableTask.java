package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericExecutableTask;

public interface ExecutableTask extends SimpleTask, 
                                        GenericExecutableTask<Task, 
                                                              SimpleTask, 
                                                              ExecutableTask, 
                                                              TaskGroup, 
                                                              TaskGraph>  {

}
