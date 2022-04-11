package ca.ntro.core.task_graphs.base;

import ca.ntro.core.graphs.common.NodeIdNtro;
import ca.ntro.core.identifyers.Key;

public class TaskIdNtro extends NodeIdNtro implements TaskId {

	public TaskIdNtro(Key key) {
		super(key);
	}

	public TaskIdNtro(String key) {
		super(key);
	}
}
