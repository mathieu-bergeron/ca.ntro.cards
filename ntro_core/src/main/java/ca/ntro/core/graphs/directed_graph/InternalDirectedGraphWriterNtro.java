package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphWriterOptions;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.graph.GenericInternalGraphWriterNtro;

public class      InternalDirectedGraphWriterNtro<N extends  DirectedNode<N,E>, 
                                                  E extends  DirectedEdge<N,E>>

       extends    GenericInternalGraphWriterNtro<N,E,DirectedGraphSearchOptions,DirectedGraphWriterOptions>

       implements InternalDirectedGraphWriter<N,E> {

}
