package ca.ntro.app.tasks;

import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskContainer;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;

public class TaskDescriptorNtro<O>  implements TaskDescriptor<O> {
	
	private Class<O> type = null;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Class<O> getType() {
		return type;
	}

	public void setType(Class<O> type) {
		this.type = type;
	}

	public TaskDescriptorNtro(String id) {
		setId(id);
	}

	public TaskDescriptorNtro(String id, Class<?> type) {
		setId(id);
	}



	@Override
	public String id() {
		return getId();
	}

	@Override
	public boolean hasType() {
		return getType() != null;
	}

	@Override
	public Class<O> type() {
		return getType();
	}

	@Override
	public Task newTask(TaskContainer parent) {
		return null;
	}

}
