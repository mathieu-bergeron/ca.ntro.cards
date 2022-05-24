package ca.ntro.cards.common.test_cases.execution.jobs;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution.TestCaseJobEngine;
import ca.ntro.cards.common.test_cases.execution.TestCaseHandler;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceFull;
import ca.ntro.core.initialization.Ntro;

public class TestCaseCreationJob<EXECUTABLE_MODEL extends CommonExecutableModel,
                                  STUDENT_MODEL extends EXECUTABLE_MODEL,
                                  TEST_CASE extends TestCase>  


       extends TestCaseJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> {

	private Class<EXECUTABLE_MODEL> executableModelClass;
	private Class<STUDENT_MODEL> studentModelClass;
	private Class<TEST_CASE> testCaseClass;
	
	private TestCaseDescriptor descriptor;
	private TestCaseHandler handler;

	private transient TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine;

	public Class<EXECUTABLE_MODEL> getExecutableModelClass() {
		return executableModelClass;
	}

	public void setExecutableModelClass(Class<EXECUTABLE_MODEL> executableModelClass) {
		this.executableModelClass = executableModelClass;
	}

	public Class<STUDENT_MODEL> getStudentModelClass() {
		return studentModelClass;
	}

	public void setStudentModelClass(Class<STUDENT_MODEL> studentModelClass) {
		this.studentModelClass = studentModelClass;
	}

	public Class<TEST_CASE> getTestCaseClass() {
		return testCaseClass;
	}

	public void setTestCaseClass(Class<TEST_CASE> testCaseClass) {
		this.testCaseClass = testCaseClass;
	}

	public TestCaseDescriptor getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(TestCaseDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public TestCaseHandler getHandler() {
		return handler;
	}

	public void setHandler(TestCaseHandler handler) {
		this.handler = handler;
	}

	public TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> getExecutionEngine() {
		return executionEngine;
	}

	public void setExecutionEngine(TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine) {
		this.executionEngine = executionEngine;
	}

	public void createTestCase() {
		
		STUDENT_MODEL model = Ntro.factory().newInstance(studentModelClass);
		TEST_CASE testCase = Ntro.factory().newInstance(testCaseClass);

		model.generateTestCase(descriptor);

		testCase.setCategory(descriptor.category());
		testCase.setSize(model.testCaseSize());
		testCase.setTestCaseId(descriptor.testCaseId());
		testCase.registerStudentModel(model);
		testCase.registerExecutableModelClass(executableModelClass);

		ExecutionTraceFull<EXECUTABLE_MODEL> trace = new ExecutionTraceFull<>();
		
		// XXX: push a EXECUTABLE_MODEL. This data can act as solutions
		//      (i.e. work in projects where the solution class is not accessible)
		EXECUTABLE_MODEL snapshot = Ntro.factory().newInstance(executableModelClass);
		snapshot.copyDataFrom(model);

		trace.pushReferenceTo(snapshot);
		testCase.setTrace(trace);
		
		executionEngine.runTestCase(testCase);

		handler.handle(testCase);
	}

}
