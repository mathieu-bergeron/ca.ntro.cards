package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;

public interface GenericTaskGraph<T  extends GenericTask<T,ST,ET,TG,G>,
                                   ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                   ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                   TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                   G  extends GenericTaskGraph<T,ST,ET,TG,G>> 

       extends   GenericTaskContainer<T,ST,ET,TG,G> {

	void setGraphName(String graphName);

	void write(GraphWriter writer);
	void write(GraphWriter writer, GenericTaskGraphWriterOptions<T,ST,ET,TG,G> options);

	TaskGraphTrace newTrace();
}
