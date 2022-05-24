package ca.ntro.cards.common.test_cases.execution;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTrace;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceFull;
import ca.ntro.core.initialization.Ntro;

public class ExecutionEngine<EXECUTABLE_MODEL extends CommonExecutableModel,
                             STUDENT_MODEL extends EXECUTABLE_MODEL,
                             TEST_CASE extends TestCase>  {
	
	private Class<EXECUTABLE_MODEL> executableModelClass;
	private Class<STUDENT_MODEL> studentModelClass;
	private Class<TEST_CASE> testCaseClass;
	
	private Map<Long, TEST_CASE> testCaseByThreadId = new HashMap<>();
	
	public Class<EXECUTABLE_MODEL> executableModelClass() {
		return executableModelClass;
	}

	public void registerExecutableModelClass(Class<EXECUTABLE_MODEL> executableModelClass) {
		this.executableModelClass = executableModelClass;
	}

	public Class<STUDENT_MODEL> studentModelClass() {
		return studentModelClass;
	}

	public void registerStudentModelClass(Class<STUDENT_MODEL> studentModelClass) {
		this.studentModelClass = studentModelClass;
	}

	public Class<TEST_CASE> testCaseClass() {
		return testCaseClass;
	}

	public void registerTestCaseClass(Class<TEST_CASE> testCaseClass) {
		this.testCaseClass = testCaseClass;
	}

	public void addStep(long threadId) {
		TEST_CASE testCase = testCaseByThreadId.get(threadId);

		testCase.addStep();
	}

	public void initialize(int initialNumberOfThreads) {

	}

	public void runTestCase(TEST_CASE testCase) {
		testCaseByThreadId.put(Thread.currentThread().getId(), testCase);
		
		testCase.run();
	}

	public TEST_CASE createTestCase(TestCaseDescriptor descriptor) {

		STUDENT_MODEL model = Ntro.factory().newInstance(studentModelClass);
		TEST_CASE testCase = Ntro.factory().newInstance(testCaseClass());

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
		
		runTestCase(testCase);

		return testCase;
	}

}
