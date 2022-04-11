package ca.ntro.app.models;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.app.NtroApp;
import ca.ntro.app.tasks.TaskFactoryNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.generic_task_graph.SimpleTaskOptions;
import ca.ntro.core.task_graphs.task_graph.SimpleTaskNtro;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTraceNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskResultsLock;
import ca.ntro.core.task_graphs.task_graph_trace.TaskResultsMessageHandler;

import static ca.ntro.app.tasks.backend.BackendTasks.*;
import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

public class ModelRegistrarNtro implements ModelRegistrar {
	
	private Set<Class<? extends Model>> modelClasses = new HashSet<>();
	private Set<Class<? extends Value>> valueClasses = new HashSet<>();

	public Set<Class<? extends Model>> getModelClasses() {
		return modelClasses;
	}

	public void setModelClasses(Set<Class<? extends Model>> modelClasses) {
		this.modelClasses = modelClasses;
	}

	public Set<Class<? extends Value>> getValueClasses() {
		return valueClasses;
	}

	public void setValueClasses(Set<Class<? extends Value>> valueClasses) {
		this.valueClasses = valueClasses;
	}
	

	@Override
	public <M extends Model> void registerModel(Class<M> modelClass) {
		getModelClasses().add(modelClass);
		Ntro.factory().registerNamedClass(modelClass);
	}

	@Override
	public <V extends Value> void registerValue(Class<V> valueClass) {
		getValueClasses().add(valueClass);
		Ntro.factory().registerNamedClass(valueClass);
	}

	public void addModelObserverTasks(TaskFactoryNtro<?> taskFactory) {
		for(Class<? extends Model> modelClass : getModelClasses()) {
			
			Task updateHandler = taskFactory.orphanTask(modified(modelClass), 
								                         SimpleTaskOptions.taskClass(SimpleTaskNtro.class)
												                          .traceClass(TaskTraceNtro.class)
												                          .resultsClass(TaskResultsMessageHandler.class))

			                                  .getTask();

			NtroApp.messageService().registerObserverTask(Ntro.reflection().simpleName(modelClass), updateHandler.asSimpleTask());

		}
	}

	public void addModelTasks(TaskFactoryNtro<?> taskFactory) {
		for(Class<? extends Model> modelClass : getModelClasses()) {

			taskFactory.orphanTask(model(modelClass), 
								   SimpleTaskOptions.taskClass(SimpleTaskNtro.class)
												    .traceClass(TaskTraceNtro.class)
												    .resultsClass(TaskResultsLock.class));
		}
	}

	public void watchModels() {
		for(Class<? extends Model> modelClass : getModelClasses()) {
			NtroApp.models().watch(modelClass);
		}
	}

}
