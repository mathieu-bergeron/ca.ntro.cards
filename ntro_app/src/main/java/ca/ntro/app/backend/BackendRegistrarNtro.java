package ca.ntro.app.backend;

import ca.ntro.app.messages.MessageRegistrarNtro;
import ca.ntro.app.models.ModelRegistrarNtro;
import ca.ntro.app.tasks.backend.BackendTaskFactory;

public class BackendRegistrarNtro implements BackendRegistrar {

	private BackendTaskFactory taskFactory = new BackendTaskFactory();
	private MessageRegistrarNtro messageRegistrar;
	private ModelRegistrarNtro modelRegistrar;
	private Backend backend = new DefaultBackend();

	public MessageRegistrarNtro getMessageRegistrar() {
		return messageRegistrar;
	}

	public void setMessageRegistrar(MessageRegistrarNtro messageRegistrar) {
		this.messageRegistrar = messageRegistrar;
	}

	public ModelRegistrarNtro getModelRegistrar() {
		return modelRegistrar;
	}

	public void setModelRegistrar(ModelRegistrarNtro modelRegistrar) {
		this.modelRegistrar = modelRegistrar;
	}

	public BackendTaskFactory getTaskFactory() {
		return taskFactory;
	}

	public void setTaskFactory(BackendTaskFactory taskFactory) {
		this.taskFactory = taskFactory;
	}

	public Backend getBackend() {
		return backend;
	}

	public void setBackend(Backend backend) {
		this.backend = backend;
	}
	
	public BackendRegistrarNtro() {
	}

	public BackendRegistrarNtro(ModelRegistrarNtro modelRegistrar, MessageRegistrarNtro messageRegistrarNtro) {
		setModelRegistrar(modelRegistrar);
		setMessageRegistrar(messageRegistrarNtro);
	}

	@Override
	public void registerBackend(Backend backend) {
		if(backend.isLocalBackend()) {
			
			getModelRegistrar().addModelTasks(getTaskFactory());
			getMessageRegistrar().addMessageHandlerTasks(getTaskFactory());
			
			backend.asLocalBackend().createTasks(getTaskFactory().asTasks());
		}

		setBackend(backend);
	}

	public void prepareToExecuteTasks() {

		getTaskFactory().prepareToExecuteTasks();
	}

	public void executeTasks() {
		
		getTaskFactory().executeTasks();
		
		if(getBackend().isLocalBackend()) {
			
			getBackend().asLocalBackend().execute();
		}
	}

	public void writeGraph() {
		getTaskFactory().writeGraph();
	}

	public void openConnection() {
		if(getBackend().isRemoteBackend()) {
			
			getBackend().asRemoteBackend().openConnection();

		}
	}

	public boolean isRemoteBackend() {
		return getBackend().isRemoteBackend();
	}

}
