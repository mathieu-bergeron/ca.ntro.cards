package ca.ntro.app.tasks.backend;

import ca.ntro.app.tasks.SimpleTaskDescriptorNtro;

public class      BackendSimpleTaskDescriptorNtro<O>

       extends    SimpleTaskDescriptorNtro<O>

       implements BackendSimpleTaskDescriptor<O> {
	

	public BackendSimpleTaskDescriptorNtro(String id) {
		super(id);
	}

}
