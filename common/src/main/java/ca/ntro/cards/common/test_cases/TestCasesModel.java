package ca.ntro.cards.common.test_cases;

import java.io.Serializable;

import ca.ntro.app.models.Model;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution.DoneHandler;
import ca.ntro.cards.common.test_cases.execution.TestCaseJobEngine;
import ca.ntro.cards.common.test_cases.execution.jobs.TestCaseCreationJob;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceFull;
import ca.ntro.cards.common.test_cases.indexing.TestCaseById;
import ca.ntro.cards.common.test_cases.indexing.TestCasesByCategory;
import ca.ntro.core.initialization.Ntro;

public abstract class      TestCasesModel<EXECUTABLE_MODEL extends CommonExecutableModel, 
                                          STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                          TEST_CASE        extends TestCase> 


                implements Model, Serializable {
	
	private long version = 0;
	
	private TestCaseById<EXECUTABLE_MODEL, TEST_CASE> testCasesById = new TestCaseById<>();
	private TestCasesByCategory<EXECUTABLE_MODEL, TEST_CASE> testCasesByCategory = new TestCasesByCategory<>();

	private Class<EXECUTABLE_MODEL> executableModelClass;
	private Class<STUDENT_MODEL> studentModelClass;
	
	private transient TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine;
	
	private transient DoneHandler onGenerationDoneHandler;
	private transient DoneHandler onWritingDoneHandler;

	public TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine() {
		return executionEngine;
	}

	public void registerExecutionEngine(TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine) {
		this.executionEngine = executionEngine;
	}

	public void registerStudentModelClass(Class<STUDENT_MODEL> studentModelClass) {
		this.studentModelClass = studentModelClass;
	}
	
	public void registerExecutableModelClass(Class<EXECUTABLE_MODEL> executableModelClass) {
		this.executableModelClass = executableModelClass;
	}
	
	protected Class<EXECUTABLE_MODEL> executableModelClass(){
		return executableModelClass;
	}
	
	protected Class<STUDENT_MODEL> studentModelClass(){
		return studentModelClass;
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
	

	public void generateFirstVersionIfNeeded() {
		throw new RuntimeException("DEPRECATED");
	}

	public void generateTestCasesAsync(DoneHandler doneHandler) {
		executionEngine.setDoneHandler(doneHandler);
		
		executionEngine.prepareToGenerateTestCases();

		describeTestCasesToGenerate();

		executionEngine.generateTestCases(doneHandler);

	}

	public abstract void describeTestCasesToGenerate();

	protected void addTestCase(TestCaseDescriptor descriptor) {
		TestCaseCreationJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> creationJob = new TestCaseCreationJob<>();
		creationJob.setExecutableModelClass(executableModelClass);
		creationJob.setStudentModelClass(studentModelClass);
		creationJob.setTestCaseClass(testCaseClass);
		creationJob.setDescriptor(descriptor);
		creationJob.setHandler(testCaseHandler);
		creationJob.setExecutionEngine(executionEngine);
		
		executionEngine.executeJob(creationJob, () -> {
			
			testCasesById.addTestCase(testCase);
			testCasesByCategory.addTestCase(testCase);
			
			TestCaseWritingJob writingJob = new TestCaseWritingJob();
			
			executionEngine.executeJob(writingJob, () -> {
				
				
			});
		});
	}

	public void writeTestCasesAsync(boolean shouldSaveJson, DoneHandler doneHandler) {
		
		executionEngine.setDoneHandler(doneHandler);
		
		testCasesById.testCases().forEach(testCase -> {
			
			executionEngine.writeTestCaseAsync(testCase, shouldSaveJson);

		});

		executionEngine.writeTestCases();
	}

	public void queueTestCaseCreationTasks() {
	}

	public void onGenerationDone(DoneHandler onGenerationDoneHandler) {
		this.onGenerationDoneHandler = onGenerationDoneHandler;

	}

	public void onWritingDone(DoneHandler onWritingDoneHandler) {
		this.onWritingDoneHandler = onWritingDoneHandler;
	}
}
