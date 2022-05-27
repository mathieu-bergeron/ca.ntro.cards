package ca.ntro.cards.common.test_cases.execution.jobs;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTraceFull;
import ca.ntro.core.initialization.Ntro;

public class CreationJob<EXECUTABLE_MODEL extends CommonExecutableModel,
                                    STUDENT_MODEL extends EXECUTABLE_MODEL,
                                    TEST_CASE extends CommonTestCase>  

       extends ExecutionJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> {

	@Override
	public void runImpl() {
	}

}
