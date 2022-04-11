package ca.ntro.core.task_graphs.generic_task_graph;

public interface GenericTaskGroup<T  extends GenericTask<T,ST,ET,TG,G>,
                                  ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                  ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                  TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                  G  extends GenericTaskGraph<T,ST,ET,TG,G>> 


       extends  GenericTask<T,ST,ET,TG,G>,
                GenericTaskContainer<T,ST,ET,TG,G> {
	

}
