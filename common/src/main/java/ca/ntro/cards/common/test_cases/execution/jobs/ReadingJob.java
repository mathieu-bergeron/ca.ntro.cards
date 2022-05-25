package ca.ntro.cards.common.test_cases.execution.jobs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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
    private TEST_CASE testCase;

	@Override
	public void runImpl() {
		try {

			FileInputStream fileInput = new FileInputStream(testCaseFile);
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			testCase = (TEST_CASE) objectInput.readObject();

			objectInput.close();

		} catch (IOException | ClassNotFoundException e) {
			
			Ntro.throwException(e);

		}
		
		System.out.println("loaded testCase: " + testCase);
	}

	public void registerFile(File testCaseFile) {
		this.testCaseFile = testCaseFile;
	}

	public String testCaseId() {
		return testCaseFile.getName();
	}


}
