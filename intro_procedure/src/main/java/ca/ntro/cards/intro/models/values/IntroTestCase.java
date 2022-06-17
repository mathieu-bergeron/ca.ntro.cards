package ca.ntro.cards.intro.models.values;


import ca.ntro.cards.intro.models.Intro;
import ca.ntro.cards.intro.test_cases.descriptor.IntroTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.descriptor.CommonTestCaseDescriptor;
import ca.ntro.cards.intro.models.IntroProcedureDashboardModel;
import ca.ntro.cards.test_cases.ProcedureTestCase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public class   IntroTestCase<STUDENT_MODEL extends Intro> 

       extends ProcedureTestCase<Intro, STUDENT_MODEL, ProcedureExecutionTrace, IntroProcedureDashboardModel> {


    @Override
    protected CommonTestCaseDescriptor newTestCaseDescriptor() {
        return new IntroTestCaseDescriptor();
    }


}
