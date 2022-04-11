package ca.ntro.app.tasks.backend;

import ca.ntro.app.tasks.TaskGroupDescriptorNtro;

public class      BackendTaskGroupDescriptorNtro<O>

       extends    TaskGroupDescriptorNtro

       implements BackendTaskGroupDescriptor<O> {


	public BackendTaskGroupDescriptorNtro(String id) {
		super(id);
	}

}
