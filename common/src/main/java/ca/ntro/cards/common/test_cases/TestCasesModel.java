package ca.ntro.cards.common.test_cases;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import ca.ntro.app.models.Model;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution.DoneHandler;
import ca.ntro.cards.common.test_cases.execution.TestCaseJobEngine;
import ca.ntro.cards.common.test_cases.execution.jobs.TestCaseCreationJob;
import ca.ntro.cards.common.test_cases.execution.jobs.TestCaseWritingJob;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceFull;
import ca.ntro.cards.common.test_cases.indexing.TestCaseById;
import ca.ntro.cards.common.test_cases.indexing.TestCasesByCategory;
import ca.ntro.core.initialization.Ntro;
import io.vertx.core.impl.ConcurrentHashSet;

public abstract class      TestCasesModel<EXECUTABLE_MODEL extends CommonExecutableModel, 
                                          STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                          TEST_CASE        extends TestCase> 


                implements Model, Serializable {
	
	private long version = 0;
	
	private TestCaseById<EXECUTABLE_MODEL, TEST_CASE> testCasesById = new TestCaseById<>();
	private TestCasesByCategory<EXECUTABLE_MODEL, TEST_CASE> testCasesByCategory = new TestCasesByCategory<>();

	private Class<EXECUTABLE_MODEL> executableModelClass;
	private Class<STUDENT_MODEL> studentModelClass;
	private Class<TEST_CASE> testCaseClass;
	
	private transient TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine;
	
	private transient Map<String, TestCaseCreationJob> creationJobs = new ConcurrentHashMap<>();
	private transient Map<String, TestCaseWritingJob> writingJobs = new ConcurrentHashMap<>();

	private transient Set<String> creationJobsDone = new ConcurrentHashSet<>();
	private transient Set<String> writingJobsDone = new ConcurrentHashSet<>();
	
	private transient DoneHandler onCreationDoneHandler;
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

		STUDENT_MODEL model = Ntro.factory().newInstance(studentModelClass);

		TEST_CASE testCase = Ntro.factory().newInstance(testCaseClass);
		
		TestCaseCreationJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> creationJob = new TestCaseCreationJob<>();
		creationJob.setTestCase(testCase);
		
		
		creationJobs.put(descriptor.testCaseId(), creationJob);

		TestCaseWritingJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> writingJob = new TestCaseWritingJob<>();
		writingJob.setTestCase(testCase);
		
		writingJobs.put(descriptor.testCaseId(), writingJob);

	}

	public void createTestCaseGenerationTasks() {
		describeTestCasesToGenerate();
	}

	public void runTestCaseGenerationTasks() {
		for(Map.Entry<String, TestCaseCreationJob> entry : creationJobs.entrySet()) {

			String testCaseId = entry.getKey();
			TestCaseCreationJob creationJob = entry.getValue();
			
			executionEngine.executeJob(creationJob, () -> {
				onCreationJobDone(testCaseId);
				
				TestCaseWritingJob writingJob = writingJobs.get(testCaseId);
				
				executionEngine.executeJob(writingJob, () -> {
					onWritingJobDone(testCaseId);
				});
			});
		}
	}

	private void onCreationJobDone(String testCaseId) {
		creationJobsDone.add(testCaseId);
		
		if(creationJobsDone.size() >= creationJobs.size()
				&& onCreationDoneHandler != null) {
			
			onCreationDoneHandler.done();
			
		}
	}
	
	private void onWritingJobDone(String testCaseId) {
		writingJobsDone.add(testCaseId);
		
		if(writingJobsDone.size() >= writingJobs.size()
				&& onWritingDoneHandler != null) {
			
			onWritingDoneHandler.done();
		}
	}

	public void onCreationDone(DoneHandler onGenerationDoneHandler) {
		this.onCreationDoneHandler = onGenerationDoneHandler;

	}

	public void onWritingDone(DoneHandler onWritingDoneHandler) {
		this.onWritingDoneHandler = onWritingDoneHandler;
	}
}
