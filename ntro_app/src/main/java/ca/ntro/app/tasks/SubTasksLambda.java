package ca.ntro.app.tasks;


public interface SubTasksLambda<TASKS extends Object> {

	void createSubTasks(TASKS subTasks);

}
