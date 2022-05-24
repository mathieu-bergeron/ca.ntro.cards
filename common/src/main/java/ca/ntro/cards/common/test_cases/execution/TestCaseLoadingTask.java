package ca.ntro.cards.common.test_cases.execution;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceFull;
import ca.ntro.core.initialization.Ntro;

public class TestCaseLoadingTask<EXECUTABLE_MODEL extends CommonExecutableModel,
                                  STUDENT_MODEL extends EXECUTABLE_MODEL,
                                  TEST_CASE extends TestCase>  


       extends TestCaseTask<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> {

}
