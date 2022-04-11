package ca.ntro.app.tasks;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.values.ObjectMap;

public class TaskInputsNtro implements TaskInputs {
	
	private ObjectMap inputs;

	public ObjectMap getInputs() {
		return inputs;
	}

	public void setInputs(ObjectMap inputs) {
		this.inputs = inputs;
	}
	
	
	


	public TaskInputsNtro(ObjectMap inputs) {
		setInputs(inputs);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <O> O get(TaskDescriptor<O> task) {
		return (O) get(task.id());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object get(String id) {
		Object result = getInputs().get(id);
		
		if(result == null) {
			Ntro.exceptionService().throwException(new RuntimeException("\n\n\tinput not found: " + id + "   Please check TaskGraph and correct dependancies\n\n"));
		}
		
		
		return result;
	}

}
