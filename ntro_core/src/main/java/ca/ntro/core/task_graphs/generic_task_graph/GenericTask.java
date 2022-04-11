package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.stream.Stream;

public interface GenericTask<T  extends GenericTask<T,ST,ET,TG,G>,
                              ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                              ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                              TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                              G  extends GenericTaskGraph<T,ST,ET,TG,G>> {

	String id();
	G parentGraph();
	
	boolean isTaskGroup();
	boolean isSimpleTask();
	TG asTaskGroup();
	ST asSimpleTask();

	void addPreviousTask(T previousTask);
	void addNextTask(T nextTask);

	Stream<T>  previousTasks();
	Stream<T>  nextTasks();

	Stream<T> parentTasks();

	Stream<T> reachableTasks();
	Stream<T> reachableTasks(TaskGraphSearchOptions options);

}