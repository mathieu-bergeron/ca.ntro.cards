package ca.ntro.cards.common.test_cases;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import ca.ntro.app.NtroApp;
import ca.ntro.app.models.Model;
import ca.ntro.app.models.Value;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.messages.MsgRefreshDashboard;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.enums.Mode;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution.TestCaseJobEngine;
import ca.ntro.cards.common.test_cases.execution.handlers.DoneHandler;
import ca.ntro.cards.common.test_cases.execution.jobs.ExecutionJob;
import ca.ntro.cards.common.test_cases.execution.jobs.ReadingJob;
import ca.ntro.cards.common.test_cases.execution.jobs.WritingJob;
import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTrace;
import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTraceFull;
import ca.ntro.cards.common.test_cases.indexing.TestCaseById;
import ca.ntro.cards.common.test_cases.indexing.TestCasesByCategory;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Stream;

public abstract class      CommonTestCaseDatabase<EXECUTABLE_MODEL extends CommonExecutableModel, 
                                                  STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                                  TEST_CASE        extends CommonTestCase,
                                                  EXECUTION_TRACE  extends CommonExecutionTrace> 


                implements Value, Serializable {
	
	private String currentTestCaseId = "ex01";
	
	private TestCaseById<EXECUTABLE_MODEL, TEST_CASE> testCasesById = new TestCaseById<>();
	private TestCasesByCategory<EXECUTABLE_MODEL, TEST_CASE> testCasesByCategory = new TestCasesByCategory<>();

	private Class<EXECUTABLE_MODEL> executableModelClass;
	private Class<STUDENT_MODEL> studentModelClass;
	private Class<TEST_CASE> testCaseClass;
	private Class<? extends EXECUTION_TRACE> executionTraceClass;
	
	private transient TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine;
	
	private transient Map<String, ExecutionJob> creationJobs = new ConcurrentHashMap<>();
	private transient Map<String, WritingJob> writingJobs = new ConcurrentHashMap<>();

	private transient Set<String> creationJobsDone = Collections.synchronizedSet(new HashSet<>());
	private transient Set<String> writingJobsDone = Collections.synchronizedSet(new HashSet<>());
	
	private transient DoneHandler onCreationDoneHandler;
	private transient DoneHandler onWritingDoneHandler;
	
	private transient boolean shouldWriteJson = false;

	private transient boolean shouldRefreshDashboard = true;
	

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

	public Class<? extends EXECUTION_TRACE> getExecutionTraceClass() {
		return executionTraceClass;
	}

	public void setExecutionTraceClass(Class<? extends EXECUTION_TRACE> executionTraceClass) {
		this.executionTraceClass = executionTraceClass;
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

	public Class<TEST_CASE> testCaseClass() {
		return testCaseClass;
	}

	public void registerTestCaseClass(Class<TEST_CASE> testCaseClass) {
		this.testCaseClass = testCaseClass;
	}
	

	public String getCurrentTestCaseId() {
		return currentTestCaseId;
	}

	public void setCurrentTestCaseId(String currentTestCaseId) {
		this.currentTestCaseId = currentTestCaseId;
	}

	public void generateFirstVersionIfNeeded() {

	}

	public abstract void describeTestCasesToGenerate();

	@SuppressWarnings("unchecked")
	protected void addTestCaseDescriptor(AbstractTestCaseDescriptor descriptor) {

		STUDENT_MODEL studentModel = Ntro.factory().newInstance(studentModelClass);
		TEST_CASE testCase = Ntro.factory().newInstance(testCaseClass);
		
		studentModel.initializeAsTestCase(descriptor);

		testCase.setCategory(descriptor.category());
		testCase.setInputSize(studentModel.testCaseSize());
		testCase.setTestCaseId(descriptor.testCaseId());
		testCase.registerStudentModel(studentModel);
		testCase.registerExecutableModelClass(executableModelClass);
		testCase.registerExecutionTraceClass(executionTraceClass);


		testCase.addExecutionStep(Mode.MANUAL);
		
		ExecutionJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> creationJob = new ExecutionJob<>();
		creationJob.setTestCase(testCase);
		
		
		creationJobs.put(descriptor.testCaseId(), creationJob);

		WritingJob<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> writingJob = new WritingJob<>();
		writingJob.setTestCase(testCase);
		writingJob.registerShouldWriteJson(shouldWriteJson);
		
		writingJobs.put(descriptor.testCaseId(), writingJob);

	}

	public void createTestCaseGenerationTasks() {
		describeTestCasesToGenerate();
	}

	public void runTestCaseGenerationTasks() {
		if(creationJobs.isEmpty()) {
			if(onCreationDoneHandler != null) {
				onCreationDoneHandler.done();
			}
			if(onWritingDoneHandler != null) {
				onWritingDoneHandler.done();
			}
			return;
		}

		for(Map.Entry<String, ExecutionJob> entry : creationJobs.entrySet()) {

			String testCaseId = entry.getKey();
			ExecutionJob creationJob = entry.getValue();
			
			executionEngine.executeJob(creationJob, () -> {
				onCreationJobDone(testCaseId);
				
				WritingJob writingJob = writingJobs.get(testCaseId);
				
				executionEngine.executeJob(writingJob, () -> {
					onWritingJobDone(testCaseId);
				});
			});
		}

		executionEngine.start();
	}

	private synchronized void onCreationJobDone(String testCaseId) {

		creationJobsDone.add(testCaseId);
		
		if(creationJobsDone.size() >= creationJobs.size()
				&& onCreationDoneHandler != null) {
			
			onCreationDoneHandler.done();
			onCreationDoneHandler = null;
		}
	}
	
	private synchronized void onWritingJobDone(String testCaseId) {
		writingJobsDone.add(testCaseId);
		
		if(writingJobsDone.size() >= writingJobs.size()
				&& onWritingDoneHandler != null) {

			onWritingDoneHandler.done();
			onWritingDoneHandler = null;
		}
	}

	public void onCreationDone(DoneHandler onGenerationDoneHandler) {
		this.onCreationDoneHandler = onGenerationDoneHandler;

	}

	public void onWritingDone(DoneHandler onWritingDoneHandler) {
		this.onWritingDoneHandler = onWritingDoneHandler;
	}

	public void registerShouldWriteJson(boolean shouldWriteJson) {
		this.shouldWriteJson = shouldWriteJson;
	}

	public void loadFromDbDir() {
		MsgRefreshDashboard msgRefreshDashboard = NtroApp.newMessage(MsgRefreshDashboard.class);
		Timer refreshTimer = new Timer();
		long startTime = System.currentTimeMillis();

		File dbDir = new File(CommonConstants.TEST_CASE_DATABASE_DIR);
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("bin")
						&& !name.equals(currentTestCaseId + ".bin");
			}
		};
		
		File[] filesToLoad = dbDir.listFiles(filter);
		int numberOfTestCases = filesToLoad.length + 1; // + currentTestCaseId.bin

		refreshTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				msgRefreshDashboard.send();
				
				if(numberOfTestCases() >= numberOfTestCases) {
					refreshTimer.cancel();
					
					long endTime = System.currentTimeMillis();
					
					System.out.print("\n\n[LOADING TEST CASES]");
					System.out.println(String.format(" done in %.2f seconds\n", ((double)(endTime - startTime)) / 1E3));
				}
			}
		}, 0, CommonConstants.TEST_CASE_LOADING_REFRESH_TIMER_DELAY_MILISECONDS);
		
		for(File testCaseFile : filesToLoad) {
			ReadingJob readingJob = new ReadingJob();
			readingJob.registerFile(testCaseFile);

			executionEngine.executeJob(readingJob, () -> {
				
				addTestCase((TEST_CASE) readingJob.getTestCase());
				
				shouldRefreshDashboard = true;

			});
		}
	}

	public void stepForward() {
		currentTestCase().stepForward(Mode.MANUAL);
	}

	public TEST_CASE currentTestCase() {
		return testCaseById((currentTestCaseId));
	}

	public void stepBackward() {
		currentTestCase().stepBackward(Mode.MANUAL);
	}

	@SuppressWarnings("unchecked")
	public void loadTestCase(String testCaseId) {
		File testCaseFile = Paths.get(CommonConstants.TEST_CASE_DATABASE_DIR, testCaseId + ".bin").toFile();

		ReadingJob readingJob = new ReadingJob();
		readingJob.registerFile(testCaseFile);
		
		readingJob.runImpl();

		addTestCase((TEST_CASE) readingJob.getTestCase());
	}

	private void addTestCase(TEST_CASE testCase) {
		testCasesById.addTestCase(testCase);
		testCasesByCategory.addTestCase(testCase);
	}

	protected TEST_CASE testCaseById(String testCaseId) {
		return testCasesById.testCaseById(testCaseId);
	}
	
	public Stream<TEST_CASE> testCases(){
		return testCasesById.testCases();
	}

	public int numberOfTestCases() {
		return testCasesById.size();
	}

}
