package ca.ntro.app.tasks;

public interface TaskFactory<TASKS extends Tasks> {

	SimpleTaskCreator<?> task(String taskId);
	<O> SimpleTaskCreator<O> task(SimpleTaskDescriptor<O> descriptor);

	TaskGroupCreator<?, TASKS> taskGroup(String taskGroupId);
	<O> TaskGroupCreator<O, TASKS> taskGroup(TaskGroupDescriptor<O> descriptor);

	TASKS asTasks();

}
