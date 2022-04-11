package ca.ntro.core.task_graphs.generic_task_graph;

public interface TaskGroupOptions<TG extends GenericTaskGroup<?,?,?,?,?>> {
                                 
	Class<TG> taskGroupClass();

	public static <TG extends GenericTaskGroup<?,?,?,?,?>> TaskGroupOptionsNtro<TG> taskGroupClass(Class<TG> taskGroupClass){
		return new TaskGroupOptionsNtro<TG>(taskGroupClass);
	}

}
