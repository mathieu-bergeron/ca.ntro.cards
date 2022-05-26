package ca.ntro.cards.common.test_cases.execution.jobs;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution.TestCaseJobEngine;
import ca.ntro.cards.common.test_cases.execution.TestCaseHandler;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceFull;
import ca.ntro.core.initialization.Ntro;

public class ExecutionJob<EXECUTABLE_MODEL extends CommonExecutableModel,
                                  STUDENT_MODEL extends EXECUTABLE_MODEL,
                                  TEST_CASE extends CommonTestCase>  


       extends Job<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> {


	@Override
	public void runImpl() {
		getTestCase().run();
	}


}
