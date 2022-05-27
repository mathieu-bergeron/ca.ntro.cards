package ca.ntro.cards.common.test_cases.execution.jobs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTraceFull;
import ca.ntro.core.initialization.Ntro;

public class ReadingJob<EXECUTABLE_MODEL extends CommonExecutableModel,
                                  STUDENT_MODEL extends EXECUTABLE_MODEL,
                                  TEST_CASE extends CommonTestCase>  


       extends Job<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> {
    	   
    private File testCaseFile;
    private TEST_CASE testCase;

	public File getTestCaseFile() {
		return testCaseFile;
	}

	public void setTestCaseFile(File testCaseFile) {
		this.testCaseFile = testCaseFile;
	}

	public TEST_CASE getTestCase() {
		return testCase;
	}

	public void setTestCase(TEST_CASE testCase) {
		this.testCase = testCase;
	}

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
