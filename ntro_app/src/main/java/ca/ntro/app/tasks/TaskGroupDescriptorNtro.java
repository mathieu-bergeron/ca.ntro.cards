package ca.ntro.app.tasks;

public class TaskGroupDescriptorNtro

       extends TaskDescriptorNtro
        
       implements TaskGroupDescriptor {


	public TaskGroupDescriptorNtro(String id) {
		super(id);
	}

	public TaskGroupDescriptorNtro(String id, Class<?> type) {
		super(id, type);
	}

}
