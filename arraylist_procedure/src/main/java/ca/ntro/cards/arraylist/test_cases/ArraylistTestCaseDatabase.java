package ca.ntro.cards.arraylist.test_cases;

import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.arraylist.models.ListeTableau;
import ca.ntro.cards.arraylist.models.ArraylistProcedureDashboardModel;
import ca.ntro.cards.arraylist.models.values.ArraylistTestCase;
import ca.ntro.cards.arraylist.test_cases.execution_trace.ArraylistExecutionTrace;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;
import ca.ntro.core.initialization.Ntro;

public class   ArraylistTestCaseDatabase<STUDENT_MODEL extends ListeTableau> 

       extends ProcedureTestCaseDatabase<ListeTableau, 
                                         STUDENT_MODEL, 
                                         ArraylistTestCase, 
                                         ArraylistExecutionTrace,
                                         ArraylistProcedureDashboardModel> {

    @Override
    public void describeTestCasesToGenerate() {
        
        AbstractTestCaseDescriptor descriptor = AbstractTestCaseDescriptor.create()
                                                          .category("exemples")
                                                          .testCaseId("ex01");
        
        addTestCaseDescriptor(descriptor);

        descriptor = AbstractTestCaseDescriptor.create()
                                                          .category("exemples")
                                                          .testCaseId("ex02");

        addTestCaseDescriptor(descriptor);


        // TODO: ajouter d'autres descriptions de cas de tests

    }

}
