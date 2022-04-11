package ca.ntro.app.tasks;

public interface TaskInputs {

	<O> O get(TaskDescriptor<O> task);
	<O> O get(String id);

}
