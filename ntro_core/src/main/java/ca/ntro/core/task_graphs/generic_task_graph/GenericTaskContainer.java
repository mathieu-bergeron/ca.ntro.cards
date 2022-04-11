package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.stream.Stream;

public interface GenericTaskContainer<T  extends GenericTask<T,ST,ET,TG,G>,
                                      ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                      ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                      TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                      G  extends GenericTaskGraph<T,ST,ET,TG,G>> {

	T findTask(String id);
	
	ST newTask(String id);
	<TT extends ST> TT newTask(String id, SimpleTaskOptions<TT> options);

	void addTask(ST simpleTask);

	TG newGroup(String id);
	<GG extends TG> GG newGroup(String id, TaskGroupOptions<GG> options);

	void addGroup(TG taskGroup);

	Stream<T> tasks();
	Stream<T> startTasks();
	Stream<T> endTasks();

}
