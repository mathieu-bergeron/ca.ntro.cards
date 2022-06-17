package ca.ntro.cards.intro.test_cases;

import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.intro.models.Intro;
import ca.ntro.cards.intro.models.IntroProcedureDashboardModel;
import ca.ntro.cards.intro.models.values.IntroTestCase;
import ca.ntro.cards.intro.test_cases.execution_trace.IntroExecutionTrace;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;
import ca.ntro.core.initialization.Ntro;

public class   IntroTestCaseDatabase<STUDENT_MODEL extends Intro> 

       extends ProcedureTestCaseDatabase<Intro, 
                                         STUDENT_MODEL, 
                                         IntroTestCase, 
                                         IntroExecutionTrace,
                                         IntroProcedureDashboardModel> {

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

        descriptor = AbstractTestCaseDescriptor.create()
                                               .category("exemples")
                                               .testCaseId("ex03");
        
        addTestCaseDescriptor(descriptor);

    }

}
