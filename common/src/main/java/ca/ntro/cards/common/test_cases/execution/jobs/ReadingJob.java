package ca.ntro.cards.common.test_cases.execution.jobs;

import java.io.File;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceFull;
import ca.ntro.core.initialization.Ntro;

public class ReadingJob<EXECUTABLE_MODEL extends CommonExecutableModel,
                                  STUDENT_MODEL extends EXECUTABLE_MODEL,
                                  TEST_CASE extends TestCase>  


       extends Job<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> {
    	   
    private File testCaseFile;

	@Override
	public void runImpl() {
	}

	public void registerFile(File testCaseFile) {
		this.testCaseFile = testCaseFile;
	}

	public String testCaseId() {
		return testCaseFile.getName();
	}


}
