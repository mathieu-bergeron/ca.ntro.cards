package ca.ntro.core.task_graphs.generic_task_graph;


public class TaskGroupOptionsNtro<TG extends GenericTaskGroup<?,?,?,?,?>>  


       implements TaskGroupOptions<TG> {
	
	private Class<TG> taskGroupClass;

	public Class<TG> getTaskGroupClass() {
		return taskGroupClass;
	}

	public void setTaskGroupClass(Class<TG> taskGroupClass) {
		this.taskGroupClass = taskGroupClass;
	}

	public TaskGroupOptionsNtro() {
	}

	public TaskGroupOptionsNtro(Class<TG> taskGroupClass) {
		setTaskGroupClass(taskGroupClass);
	}

	@Override
	public Class<TG> taskGroupClass() {
		return getTaskGroupClass();
	}
	

}
