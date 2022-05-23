package ca.ntro.cards.common.models;

import ca.ntro.app.models.Model;
import ca.ntro.cards.common.models.values.execution_trace.ExecutionTraceFull;
import ca.ntro.cards.common.models.values.test_cases.TestCase;
import ca.ntro.cards.common.models.values.test_cases.TestCaseById;
import ca.ntro.cards.common.models.values.test_cases.TestCasesByCategory;
import ca.ntro.core.initialization.Ntro;

public abstract class      TestCasesModel<EXECUTABLE_MODEL extends CommonExecutableModel, 
                                          STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                          TEST_CASE        extends TestCase<EXECUTABLE_MODEL>> 


                implements Model {
	
	private long version = 0;
	
	private TestCaseById<EXECUTABLE_MODEL, TEST_CASE> testCasesById = new TestCaseById<>();

	private TestCasesByCategory<EXECUTABLE_MODEL, TEST_CASE> testCasesByCategory = new TestCasesByCategory<>();
	
	private Class<EXECUTABLE_MODEL> executableModelClass;
	private Class<STUDENT_MODEL> studentModelClass;

	public void registerStudentModelClass(Class<STUDENT_MODEL> studentModelClass) {
		this.studentModelClass = studentModelClass;
	}
	
	public void registerExecutableModelClass(Class<EXECUTABLE_MODEL> executableModelClass) {
		this.executableModelClass = executableModelClass;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public TestCaseById<EXECUTABLE_MODEL, TEST_CASE> getTestCasesById() {
		return testCasesById;
	}

	public void setTestCasesById(TestCaseById<EXECUTABLE_MODEL, TEST_CASE> testCasesById) {
		this.testCasesById = testCasesById;
	}

	public TestCasesByCategory<EXECUTABLE_MODEL, TEST_CASE> getTestCasesByCategory() {
		return testCasesByCategory;
	}

	public void setTestCasesByCategory(TestCasesByCategory<EXECUTABLE_MODEL, TEST_CASE> testCasesByCategory) {
		this.testCasesByCategory = testCasesByCategory;
	}

	public void generateFirstVersionIfNeeded(Class<STUDENT_MODEL> studentModelClass) {
		if(version == 0) {
			generateFirstVersion(studentModelClass);
			version++;
		}
	}
	
	public void addTestCase(TestCaseDescriptor descriptor, STUDENT_MODEL model) {
		TEST_CASE testCase = emptyTestCase();
		testCase.setCategory(descriptor.category());
		testCase.setSize(model.testCaseSize());
		testCase.setTestCaseId(descriptor.testCaseId());

		ExecutionTraceFull<EXECUTABLE_MODEL> trace = new ExecutionTraceFull<>();
		
		// XXX: push a EXECUTABLE_MODEL. This data can act as solutions
		//      (i.e. work in projects where the solution class is not accessible)
		EXECUTABLE_MODEL snapshot = Ntro.factory().newInstance(executableModelClass);
		snapshot.copyDataFrom(model);

		trace.pushReferenceTo(snapshot);
		testCase.setTrace(trace);
		
		testCasesById.addTestCase(testCase);
		testCasesByCategory.addTestCase(testCase);
		
	}

	protected abstract void generateFirstVersion(Class<STUDENT_MODEL> studentModelClass);

	public abstract STUDENT_MODEL emptyStudentModel();

	public abstract TEST_CASE emptyTestCase();



}
