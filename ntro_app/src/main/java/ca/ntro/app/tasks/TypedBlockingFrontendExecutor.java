package ca.ntro.app.tasks;

public interface TypedBlockingFrontendExecutor<R extends Object> {

	R execute(TaskInputs inputs);

}
